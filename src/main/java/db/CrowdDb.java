package db;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

import crowdtrust.AccuracyRecord;
import crowdtrust.Bee;
import crowdtrust.BinaryAccuracy;
import crowdtrust.BinaryR;
import crowdtrust.SingleAccuracy;
import crowdtrust.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrowdDb {

	public static void addResponse(int account, String string, int subtask) {
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
			preparedStatement.setString(3, string);
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
			BinaryAccuracy accuracy;
			if(resultSet.next())
				accuracy = mapBinaryAccuracy(resultSet);
			else{
				accuracy = new BinaryAccuracy(0.5, 0.5, 0, 0);
				insertBinaryAccuracy(id, accuracy);
			}
			return accuracy;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void insertBinaryAccuracy(int id, BinaryAccuracy accuracy) {
		PreparedStatement preparedStatement;
		String sql = "INSERT INTO binaryaccuracies (account, truePositive, " +
				"trueNegative, numPositive, numNegative) " +
				"VALUES(?,?,?,?,?)";
		
		try {
			preparedStatement = DbAdaptor.connect().prepareStatement(sql);
			double truePositive = accuracy.getTruePositive();
			double trueNegative = accuracy.getTrueNegative();
			int positiveN = accuracy.getPositiveN();
			int negativeN = accuracy.getNegativeN();
			preparedStatement.setInt(1, id);
			preparedStatement.setDouble(2, truePositive);
			preparedStatement.setDouble(3, trueNegative);
			preparedStatement.setInt(4, positiveN);
			preparedStatement.setInt(5, negativeN);
			
			preparedStatement.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public static Collection<Bee> getAnnotators(int subtask_id) {
		PreparedStatement preparedStatement;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT account FROM responses WHERE subtask = ?");
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
			Collection<Bee> bees = new ArrayList<Bee>();
			int account;
			while(resultSet.next()) {
				account = resultSet.getInt(1);
				bees.add(new Bee(account));
			}
			return bees;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Collection<AccuracyRecord> getBinaryAccuracies(Collection<Bee> annotators) {
		Collection<AccuracyRecord> records = new ArrayList<AccuracyRecord>();
		for(Bee b : annotators) {
			int id = b.getId();
			BinaryAccuracy accuracy = getBinaryAccuracy(id);
			AccuracyRecord record = new AccuracyRecord(b, accuracy);
			records.add(record);
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
			resultSet.next(); //JOHNNN
			double accuracy = resultSet.getDouble("accuracy");
			SingleAccuracy singleAccuracy = new SingleAccuracy(accuracy);
			return singleAccuracy;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void updateBinaryAccuracies(Collection<AccuracyRecord> accuracies) {
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
			for(AccuracyRecord record : accuracies) {
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

	public static Collection<AccuracyRecord> getMultiValueAccuracies(Collection<Bee> annotators) {
		Collection<AccuracyRecord> records = new ArrayList<AccuracyRecord>();
		for(Bee bee : annotators) {
			int id = bee.getId();
			SingleAccuracy accuracy = getMultiValueAccuracy(id);
			AccuracyRecord record = new AccuracyRecord(bee, accuracy);
			records.add(record);
		}
		return records;	
	}

	public static void updateMultiValueAccuracies(Collection<AccuracyRecord> accuracies) {
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
			for(AccuracyRecord record : accuracies) {
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

	public static Collection<AccuracyRecord> getContinuousAccuracies(Collection<Bee> annotators) {
		Collection<AccuracyRecord> records = new ArrayList<AccuracyRecord>();
		for(Bee bee : annotators) {
			int id = bee.getId();
			SingleAccuracy accuracy = getContinuousAccuracy(id);
			AccuracyRecord record = new AccuracyRecord(bee, accuracy);
			records.add(record);
		}
		return records;	
	}

	public static void updateContinuousAccuracies(Collection<AccuracyRecord> accuracies) {
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
			for(AccuracyRecord record : accuracies) {
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

	public static List<Account> getAllExperts() {
		PreparedStatement preparedStatement;
		String sql = "SELECT account FROM experts";
		try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql);
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return null;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return null;
    }
		try {
			ResultSet rs = preparedStatement.executeQuery();
			List<Account> experts = new ArrayList<Account>();
			while(rs.next()) {
				int id = rs.getInt("id");
				experts.add(LoginDb.getAccount(id));
			}
			return experts; 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Account> getAllBots() {
		PreparedStatement preparedStatement;
		String sql = "SELECT account FROM bots";
		try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql);
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  return null;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  return null;
    }
		try {
			ResultSet rs = preparedStatement.executeQuery();
			List<Account> experts = new ArrayList<Account>();
			while(rs.next()) {
				int id = rs.getInt("id");
				experts.add(LoginDb.getAccount(id));
			}
			return experts; 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Collection<AccuracyRecord> getBinaryAnnotators(int id) {
		PreparedStatement preparedStatement;
		String sql = "SELECT responses.account, response, truePositive, " +
				"trueNegative, numPositive, numNegative " +
				"FROM responses JOIN binaryaccuracies " +
				"ON responses.account = binaryaccuracies.account " +
				"WHERE subtask = ?";
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql);
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
			Collection<AccuracyRecord> as = new ArrayList<AccuracyRecord>();
			int account;
			while(resultSet.next()) {
				account = resultSet.getInt(1);
				
				BinaryAccuracy accuracy;
				accuracy = mapBinaryAccuracy(resultSet);
				AccuracyRecord record = new AccuracyRecord(new Bee(account), accuracy);
				record.setMostRecent(new BinaryR(resultSet.getString("response")));
				as.add(record);
			}
			return as;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
