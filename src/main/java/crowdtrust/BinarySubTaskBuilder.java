package crowdtrust;

public class BinarySubTaskBuilder extends SubTaskBuilder{

	public BinarySubTaskBuilder(int id, double confidence_threshold,
			int number_of_labels, int max_labels) {
		super(id, confidence_threshold, number_of_labels, max_labels);
	}

	@Override
	public SubTask build() {
		BinarySubTask b = new BinarySubTask(id, confidence_threshold, 
				number_of_labels, max_labels);
		b.fileName = this.fileName;
		return b;
	}
	
}
