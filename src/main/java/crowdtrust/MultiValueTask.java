package crowdtrust;

import java.util.LinkedList;
import java.util.List;

public class MultiValueTask extends Task {
	  public MultiValueTask (int id, String name, String question, int accuracy, int media_type, int input_type, List<String> answers)
	  {
		  super(id, name, question, accuracy, 2, media_type, input_type, answers, new LinkedList<String>(), new LinkedList<String>(), 0);
	  }
}
