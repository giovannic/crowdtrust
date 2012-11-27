package crowdtrust;

import java.sql.SQLException;
import java.util.List;

public abstract class SubTask {
	
	//public Media media;
	private String html;
	private List <Bee> responses;
	private int id;
	
	public String getHtml() {
		return html;
	}

  public String getName()
  {
		return this.name;
	}
	
	/*
	 * calculates the new estimate and confidence, 
	 * if the confidence is good enough the subtask is closed
	 * */
	public abstract void estimate();
	
	public void close(){
		db.SubTaskDb.close(id);
		Task parent = db.SubTaskDb.getTask(id);
		try {
			if (db.TaskDb.checkFinished(parent.id)){
				parent.updateAccuracies();
			}
		} catch (SQLException e) {
			// grave error TODO log it
			e.printStackTrace();
		}
		
	}
	
}
