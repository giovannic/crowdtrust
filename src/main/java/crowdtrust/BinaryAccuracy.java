package crowdtrust;

public class BinaryAccuracy extends Accuracy {
	double truePositive;
	double trueNegative;
	
	BinaryAccuracy(double pos, double neg){
		truePositive = pos;
		trueNegative = neg;
	}

	@Override
	Byte[] serialise() {
		// TODO Auto-generated method stub
		return null;
	}
}
