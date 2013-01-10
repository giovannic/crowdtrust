package crowdtrust;

public abstract class Accuracy {
	protected int n;
	
	protected boolean ghost = false;
	
	public int getN(){
		return n;
	}
	
	void increaseN(){
		n++;
	}
	
	abstract Byte[] serialise();
	
	abstract double variance();
	
	abstract boolean expert(double threshold);

	public boolean isGhost() {
		return ghost;
	}

	public void setGhost() {
		this.ghost = true;
	}
	
	
}
