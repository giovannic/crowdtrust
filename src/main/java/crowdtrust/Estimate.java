package crowdtrust;

public class Estimate {
	private Response r;
	private double confidence;
	
	public Estimate(Response r, double c){
		this.setR(r);
		setConfidence(c);
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
}
