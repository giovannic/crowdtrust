package crowdtrust;

public abstract class Response {
	public abstract String serialise();
	
	@Override
	public abstract boolean equals(Object o);
}
