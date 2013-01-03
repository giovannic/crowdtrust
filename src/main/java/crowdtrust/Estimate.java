package crowdtrust;

public class Estimate {
	Response r;
	double confidence;
	
	Estimate(Response r, double c){
		this.r = r;
		confidence = c;
	}
}
