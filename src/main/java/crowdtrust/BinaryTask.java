package crowdtrust;

public class BinaryTask extends Task
{
  
  public BinaryTask(int id, String name, String question, int accuracy, int media_type, int input_type, String[] answers)
  {
	  super(id, name, question, accuracy, 1, media_type, input_type, answers);
  }
  
}
