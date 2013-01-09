package crowdtrust;

import java.util.LinkedList;

public class SingleContinuousTask extends Task {
	  public SingleContinuousTask (int id, String name, String question, int accuracy, int media_type, int input_type, int min, int max, double step)
	  {
		  super(id, name, question, accuracy, 3, media_type, input_type, new LinkedList<String>(), min, max, step);
	  }

}
