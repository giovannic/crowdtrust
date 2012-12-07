package crowdtrust;

import java.sql.SQLException;
import java.util.List;

public abstract class SubTask {
	
	//public Media media;
	protected String html;
	protected List <Bee> responses;
	protected int id;
	protected double confidence;
	protected Response best_estimate;
	
	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
	/*
	 * calculates the new estimate and confidence, 
	 * if the confidence is good enough the subtask is closed
	 * */
	public abstract void estimate(Bee annotator, Response r);
	
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
