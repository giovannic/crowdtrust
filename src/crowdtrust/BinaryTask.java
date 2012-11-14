package crowdtrust;

public class BinaryTask extends Task
{
  
  public BinaryTask()
  {
	  //for the benefit of the object mapper
  }
  
  // what class should images be stored as?!
  public BinaryTask(String name, String question)
  {
    super();
    this.name = name;
    this.question = question;
    this.addToDatabase();
  }
  
  public void assignCrowd()
  {
  }

};
