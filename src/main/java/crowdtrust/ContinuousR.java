package crowdtrust;

public class ContinuousR extends Response {

	int value;
	
	@Override
	Byte[] serialise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		ContinuousR cr = (ContinuousR) o;
		return value == cr.value;
	}

}
