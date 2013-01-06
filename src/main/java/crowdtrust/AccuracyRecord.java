package crowdtrust;

public class AccuracyRecord {
	private Bee b;
	private Accuracy a;
	private Response mostRecent;
	
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

	public Response getMostRecent() {
		return mostRecent;
	}

	public void setMostRecent(Response mostRecent) {
		this.mostRecent = mostRecent;
	}
}
