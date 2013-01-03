package crowdtrust;

public abstract class Accuracy {
	protected int n;
	
	int getN(){
		return n;
	}
	
	void increaseN(){
		n++;
	}
	
	abstract Byte[] serialise();
	
	abstract double variance();
	
	abstract boolean expert(double threshold);
}
