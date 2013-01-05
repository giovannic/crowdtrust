package crowdtrust;

import db.TaskDb;

public abstract class Task
{

  protected int id;
  protected String name;
  protected String question;
  protected int accuracy;
  protected int type;
  protected int owner_id;
  
  public Task(int id, String name, String question, int accuracy, int type){
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

  public String getQuestion()
  {
    return this.question;
  }



  public int getType()
  {
    return this.type;
  }


  public int getAccuracy()
  {
    return this.accuracy;
  }

  public boolean addToDatabase()
  {
    //return TaskDb.addTask(name, question, accuracy, type);
	  return false;
  }

  public void updateAccuracies() {
	
  }

  /*if all finished, close task*/
  public void notifyFinished() {
	// TODO Auto-generated method stub
	
  }

}
