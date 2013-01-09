package crowdtrust;

import java.util.LinkedList;
import java.util.List;

public class SingleContinuousTask extends Task {
	  public SingleContinuousTask (int id, String name, String question, int accuracy, int media_type, int input_type, List<String> mins, List<String> maxes, double step)
	  {
		  super(id, name, question, accuracy, 3, media_type, input_type, new LinkedList<String>(), mins, maxes, step);
	  }

}
