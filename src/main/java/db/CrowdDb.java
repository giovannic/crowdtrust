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

	public static void addResponse(int account, Byte[] serialise, int subtask) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO responses (account, subtask, response)");
		sql.append("VALUES(?, ?, ?)");
		Connection c;
    try {
      c = DbAdaptor.connect();
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
			return;
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
			return;
    }
		try {
			PreparedStatement preparedStatement = c.prepareStatement(sql.toString());
			preparedStatement.setInt(1, account);
			preparedStatement.setInt(2, subtask);
			preparedStatement.setObject(3, (Object) serialise, -7);
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

return null;
	}

	public static void updateBinaryAccuracies(AccuracyRecord[] accuracies) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

	}

	public static AccuracyRecord[] getMultiValueAccuracies(Bee[] annotators) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
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

return null;
	}

	public static void updateMultiValueAccuracies(AccuracyRecord[] accuracies) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static Accuracy getContinuousAccuracy(int annotatorId) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
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

return null;
	}

	public static AccuracyRecord[] getContinuousAccuracies(Bee[] annotators) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
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

return null;
	}

	public static void updateContinuousAccuracies(AccuracyRecord[] accuracies) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static void updateBinaryExperts(Collection<Bee> experts) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static void updateBinaryBots(Collection<Bee> bots) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static void updateContinuousExperts(Collection<Bee> bots) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static void updateContinuousBots(Collection<Bee> bots) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static void updateMultiValueExperts(Collection<Bee> experts) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }

		
	}

	public static void updateMultiValueBots(Collection<Bee> bots) {
		PreparedStatement preparedStatement;
StringBuilder sql = new StringBuilder();
    try {
      preparedStatement = DbAdaptor.connect().prepareStatement(sql.toString());
    }
    catch (ClassNotFoundException e) {
  	  System.err.println("Error connecting to DB on Crowd: PSQL driver not present");
  	  
    } catch (SQLException e) {
  	  System.err.println("SQL Error on Crowd");
  	  
    }
	}

	public static BinaryAccuracy mapBinaryAccuracy(ResultSet resultSet) {
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

}
