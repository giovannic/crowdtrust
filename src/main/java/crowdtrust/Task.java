package crowdtrust;

import java.util.List;

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
  protected List<String> answers;
  protected int min;
  protected int max;
  protected double step;
  
  public Task(int id, String name, String question, int accuracy, int annotation_type, int media_type, int input_type, List<String> answers, int min, int max, double step){
	  this.id = id;
	  this.name = name;
	  this.question = question;
	  this.accuracy = accuracy;
	  this.annotation_type = annotation_type;
	  this.media_type = media_type;
	  this.input_type = input_type;
	  this.answers = answers;
	  this.min = min;
	  this.max = max;
	  this.step = step;
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
  
  public List<String> getAnswers()
  {
    return this.answers;
  }

  public int getAccuracy()
  {
    return this.accuracy;
  }

  public int getMin()
  {
    return this.accuracy;
  }

  public int getMax()
  {
    return this.max;
  }

  public double getStep()
  {
    return this.step;
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
