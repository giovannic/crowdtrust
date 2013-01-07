package crowdtrust;

public abstract class Task
{

  protected int id;
  protected String name;
  protected String question;
  protected int accuracy;
  protected int owner_id;
  protected int annotation_type;
  protected int media_type;
  protected int input_type;
  protected String[] answers;
  
  public Task(int id, String name, String question, int accuracy, int annotation_type, int media_type, int input_type, String[] answers){
	  this.id = id;
	  this.name = name;
	  this.question = question;
	  this.accuracy = accuracy;
	  this.annotation_type = annotation_type;
	  this.media_type = media_type;
	  this.input_type = input_type;
	  this.answers = answers;
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



  public int getAnnotationType()
  {
    return this.annotation_type;
  }
  
  public int getMediaType()
  {
    return this.media_type;
  }
  
  public int getInputType()
  {
    return this.input_type;
  }
  
  public String[] getAnswers()
  {
    return this.answers;
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

public int getMaxLabels() {
	// TODO Auto-generated method stub
	return 0;
}

}
