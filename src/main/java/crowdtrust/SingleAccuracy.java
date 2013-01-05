package crowdtrust;

public class SingleAccuracy extends Accuracy{
	private double accuracy;
	
	public SingleAccuracy(double a){
		accuracy = a;
	}

	public double getAccuracy() {
		return this.accuracy;
	}

	public void setAccuracy(double a) {
		this.accuracy = a;
	}

	@Override
	Byte[] serialise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	double variance() {
		double alpha = n*accuracy;
		double beta = n - alpha;
		return (alpha*beta)/
				(Math.pow(alpha + beta, 2)*(alpha + beta + 1));
	}

	@Override
	boolean expert(double threshold) {
		return accuracy > threshold;
	}
}
