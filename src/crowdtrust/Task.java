package crowdtrust;

import db.TaskDb;

public abstract class Task
{

  protected int id;
  protected String name;
  protected String question;
  protected int accuracy;
  protected int type;
  
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

  public abstract void assignCrowd();

};
