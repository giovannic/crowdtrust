package db;

import java.util.Collection;

import crowdtrust.Accuracy;
import crowdtrust.AccuracyRecord;
import crowdtrust.Bee;
import crowdtrust.BinaryAccuracy;
import crowdtrust.SingleAccuracy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CrowdDb {

	public static void addResponse(int account, byte[] serialise, int subtask) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO responses (account, subtask, response)");
		sql.append("VALUES(?, ?, ?)");
		Connection c;
    try {
      c = DbAdaptor.connect();
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  e.printStackTrace();
	  return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  e.printStackTrace();
	  return;
    }
		try {
			PreparedStatement preparedStatement = c.prepareStatement(sql.toString());
			preparedStatement.setInt(1, account);
			preparedStatement.setInt(2, subtask);
			preparedStatement.setBytes(3, serialise);
			preparedStatement.execute();
			c.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static BinaryAccuracy getBinaryAccuracy(int id) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT truePositive, trueNegative, numPositive, numNegative FROM binaryaccuracies\n");
		sql.append("WHERE account = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return null;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return null;
    }
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			BinaryAccuracy accuracy = mapBinaryAccuracy(resultSet);
			return accuracy;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Bee[] getAnnotators(int subtask_id) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT account, COUNT(*) FROM responses WHERE subtask = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return null;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return null;
    }
		try {
			preparedStatement.setInt(1, subtask_id);
			ResultSet resultSet = preparedStatement.executeQuery();
			int numAnnotators = resultSet.getInt(2);
			Bee[] bees = new Bee[numAnnotators];
			int i = 0;
			int account;
			while(resultSet.next()) {
				account = resultSet.getInt(1);
				bees[i] = new Bee(account);
				i++;
			}
			return bees;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static AccuracyRecord[] getBinaryAccuracies(Bee[] annotators) {
		int annotatorsLength = annotators.length;
		AccuracyRecord[] records = new AccuracyRecord[annotatorsLength];
		for(int i = 0; i < annotatorsLength; i++) {
			Bee bee = annotators[i];
			int id = bee.getId();
			BinaryAccuracy accuracy = getBinaryAccuracy(id);
			AccuracyRecord record = new AccuracyRecord(bee, accuracy);
			records[i] = record;
		}
		return records;
	}

	public static SingleAccuracy getMultiValueAccuracy(int id) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT accuracy FROM multivalueaccuracies WHERE account = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return null;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return null;
    }
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			double accuracy = resultSet.getDouble("accuracy");
			SingleAccuracy singleAccuracy = new SingleAccuracy(accuracy);
			return singleAccuracy;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateBinaryAccuracies(AccuracyRecord[] accuracies) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE binaryaccuracies\n");
		sql.append("SET truePositive = ?, trueNegative = ?, numPositive = ?, numNegative = ?\n");
		sql.append("WHERE account = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return;
    }
		try {
			for(int i = 0; i < accuracies.length; i++) {
				AccuracyRecord record = accuracies[i];
				Bee bee = record.getBee();
				int id = bee.getId();
				BinaryAccuracy accuracy = (BinaryAccuracy) record.getAccuracy();
				double truePositive = accuracy.getTruePositive();
				double trueNegative = accuracy.getTrueNegative();
				int positiveN = accuracy.getPositiveN();
				int negativeN = accuracy.getNegativeN();
				preparedStatement.setDouble(1, truePositive);
				preparedStatement.setDouble(2, trueNegative);
				preparedStatement.setInt(3, positiveN);
				preparedStatement.setInt(4, negativeN);
				preparedStatement.setInt(5, id);
				preparedStatement.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static AccuracyRecord[] getMultiValueAccuracies(Bee[] annotators) {
		int annotatorsLength = annotators.length;
		AccuracyRecord[] records = new AccuracyRecord[annotatorsLength];
		for(int i = 0; i < annotatorsLength; i++) {
			Bee bee = annotators[i];
			int id = bee.getId();
			SingleAccuracy accuracy = getMultiValueAccuracy(id);
			AccuracyRecord record = new AccuracyRecord(bee, accuracy);
			records[i] = record;
		}
		return records;	
	}

	public static void updateMultiValueAccuracies(AccuracyRecord[] accuracies) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE multivalueaccuracies\n");
		sql.append("SET accuracy = ?\n");
		sql.append("WHERE account = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return;
    }
		try {
			for(int i = 0; i < accuracies.length; i++) {
				AccuracyRecord record = accuracies[i];
				Bee bee = record.getBee();
				int id = bee.getId();
				SingleAccuracy accuracy = (SingleAccuracy) record.getAccuracy();
				double a = accuracy.getAccuracy();
				preparedStatement.setDouble(1, a);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static SingleAccuracy getContinuousAccuracy(int annotatorId) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT accuracy FROM continuousaccuracies WHERE account = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return null;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return null;
    }
		try {
			preparedStatement.setInt(1, annotatorId);
			ResultSet resultSet = preparedStatement.executeQuery();
			double accuracy = resultSet.getDouble("accuracy");
			SingleAccuracy singleAccuracy = new SingleAccuracy(accuracy);
			return singleAccuracy;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static AccuracyRecord[] getContinuousAccuracies(Bee[] annotators) {
		int annotatorsLength = annotators.length;
		AccuracyRecord[] records = new AccuracyRecord[annotatorsLength];
		for(int i = 0; i < annotatorsLength; i++) {
			Bee bee = annotators[i];
			int id = bee.getId();
			SingleAccuracy accuracy = getContinuousAccuracy(id);
			AccuracyRecord record = new AccuracyRecord(bee, accuracy);
			records[i] = record;
		}
		return records;	
	}

	public static void updateContinuousAccuracies(AccuracyRecord[] accuracies) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE continuousaccuracies\n");
		sql.append("SET accuracy = ?\n");
		sql.append("WHERE account = ?");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return;
    }
		try {
			for(int i = 0; i < accuracies.length; i++) {
				AccuracyRecord record = accuracies[i];
				Bee bee = record.getBee();
				int id = bee.getId();
				SingleAccuracy accuracy = (SingleAccuracy) record.getAccuracy();
				double a = accuracy.getAccuracy();
				preparedStatement.setDouble(1, a);
				preparedStatement.setInt(2, id);
				preparedStatement.executeUpdate();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateBinaryExperts(Collection<Bee> experts) {
		updateExperts(experts, 1);
	}

	public static void updateContinuousExperts(Collection<Bee> experts) {
		updateExperts(experts, 2);
	}

	public static void updateMultiValueExperts(Collection<Bee> experts) {
		updateExperts(experts, 3);		
	}

	public static void updateBinaryBots(Collection<Bee> bots) {
		updateBots(bots, 1);		
	}

	public static void updateContinuousBots(Collection<Bee> bots) {
		updateBots(bots, 2);
	}

	public static void updateMultiValueBots(Collection<Bee> bots) {
		updateBots(bots, 3);
	}

	private static BinaryAccuracy mapBinaryAccuracy(ResultSet resultSet) {
		try {
		double truePositive = resultSet.getDouble("truePositive");
		double trueNegative = resultSet.getDouble("trueNegative");
		int numPositive = resultSet.getInt("numPositive");
		int numNegative = resultSet.getInt("numNegative");
		BinaryAccuracy accuracy = new BinaryAccuracy(truePositive, trueNegative, numPositive, numNegative);
		return accuracy;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void updateExperts(Collection<Bee> experts, int type) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO experts (accounts, type) VALUES (?, ?)");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return;
    }
		try {
			Bee[] bees = (Bee []) experts.toArray(new Bee[experts.size()]);
			for(int i = 0; i < bees.length; i++) {
				Bee bee = bees[i];
				int id = bee.getId();
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, type);
				preparedStatement.executeUpdate();
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void updateBots(Collection<Bee> bots, int type) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO bots (accounts, type) VALUES (?, ?)");
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return;
    }
		try {
			Bee[] bees = (Bee []) bots.toArray(new Bee[bots.size()]);
			for(int i = 0; i < bees.length; i++) {
				Bee bee = bees[i];
				int id = bee.getId();
				preparedStatement.setInt(1, id);
				preparedStatement.setInt(2, type);
				preparedStatement.executeUpdate();
			}		
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
