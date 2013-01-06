package crowdtrust;

public abstract class Response {
	public abstract byte[] serialise();
	
	@Override
	public abstract boolean equals(Object o);
}
