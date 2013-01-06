package crowdtrust;

public abstract class Response {
	abstract byte[] serialise();
	
	@Override
	public abstract boolean equals(Object o);
}
