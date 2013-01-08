package crowdtrust;

public class MultiValueTask extends Task {
	  public MultiValueTask (int id, String name, String question, int accuracy, int media_type, int input_type, String[] answers)
	  {
		  super(id, name, question, accuracy, 2, media_type, input_type, answers);
	  }
}
