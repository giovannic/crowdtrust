package crowdtrust;

public class BinaryAccuracy extends Accuracy {
	double truePositive;
	double trueNegative;
	
	double alphaPos;
	double betaPos;
	
	double alphaNeg;
	double betaNeg;
	
	double alphaAdvPos;
	double betaAdvPos;
	
	double alphaAdvNeg;
	double betaAdvNeg;
	
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
