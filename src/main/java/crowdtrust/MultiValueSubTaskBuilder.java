package crowdtrust;

public class MultiValueSubTaskBuilder extends SubTaskBuilder{

	int options;
	
	public MultiValueSubTaskBuilder(int id, double confidence_threshold,
			int number_of_labels, int max_labels) {
		super(id, confidence_threshold, number_of_labels, max_labels);
	}
	
	public void options(int options){
		this.options = options;
	}

	@Override
	public SubTask build() {
		MultiValueSubTask mv = new MultiValueSubTask(id, confidence_threshold, 
				number_of_labels, max_labels);
		mv.fileName = this.fileName;
		mv.options = this.options;
		return mv;
	}
	
}