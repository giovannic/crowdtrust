package crowdtrust;

public class Estimate {
	private Response r;
	private double confidence;
	private int frequency;
	
	public Estimate(Response r, double confidence, int frequency){
		this.setR(r);
		this.confidence = confidence;
		this.frequency = frequency;
	}

	public Response getR() {
		return r;
	}

	public void setR(Response r) {
		this.r = r;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public int getFrequency() {
		return frequency;
	}

	public void incFrequency() {
		this.frequency++;
	}
}
