package crowdtrust;

import java.util.List;

public class BinaryTask extends Task
{
  
  public BinaryTask(int id, String name, String question, int accuracy, int media_type, int input_type, List<String> answers)
  {
	  super(id, name, question, accuracy, 1, media_type, input_type, answers, 0, 0, 0);
  }
  
}
