package crowdtrust;

public class Estimate {
	Response r;
	double confidence;
	
	public Estimate(Response r, double c){
		this.r = r;
		confidence = c;
	}
}
