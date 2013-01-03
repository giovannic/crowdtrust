package crowdtrust;

public class SingleAccuracy extends Accuracy{
	double accuracy;
	
	SingleAccuracy(double a){
		accuracy = a;
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