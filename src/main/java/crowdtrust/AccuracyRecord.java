package crowdtrust;

public class AccuracyRecord {
	private Bee b;
	private Accuracy a;
	
	public AccuracyRecord(Bee b, Accuracy a){
		this.b = b;
		this.a = a;
	}

	public Bee getBee() {
		return this.b;
	}

	public Accuracy getAccuracy() {
		return this.a;
	}
}
