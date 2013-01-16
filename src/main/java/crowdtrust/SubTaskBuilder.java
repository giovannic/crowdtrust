package crowdtrust;

public abstract class SubTaskBuilder{
	
	protected int id;
	protected double confidence_threshold;
	protected int number_of_labels;
	protected int max_labels;
	protected String fileName;
	
	SubTaskBuilder(int id, double confidence_threshold, 
			int number_of_labels, int max_labels){
		this.id = id;
		this.confidence_threshold = confidence_threshold;
		this.number_of_labels = number_of_labels;
		this.max_labels = max_labels;
	}
	
	void fileName(String fileName){
		this.fileName = fileName;
	}
	
	public abstract SubTask build();
	
}