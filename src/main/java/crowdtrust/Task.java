package crowdtrust;

import db.TaskDb;

public abstract class Task
{

  protected int id;
  protected String name;
  protected String question;
  protected int accuracy;
  protected int type;
  
  Task(int id, String name, String question, int accuracy, int type){
	  this.id = id;
	  this.name = name;
	  this.question = question;
	  this.accuracy = accuracy;
	  this.type = type;
  }
  
  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  protected void addToDatabase()
  {
    TaskDb.addTask(name, question, accuracy, type);
  }

  public void updateAccuracies() {
	
  }

}
