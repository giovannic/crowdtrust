package crowdtrust;

public abstract class Response {
	abstract Byte[] serialise();
	
	@Override
	public abstract boolean equals(Object o);
}
