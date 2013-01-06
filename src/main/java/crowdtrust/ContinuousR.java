package crowdtrust;

import java.io.UnsupportedEncodingException;

public class ContinuousR extends Response {

	private int value;
	
	public ContinuousR(byte [] b) throws NumberFormatException, UnsupportedEncodingException{
		this.value = Integer.parseInt(new String(b, "UTF-8"));
	}
	
	@Override
	public String serialise() {
		return Integer.toString(value);
	}

	@Override
	public boolean equals(Object o) {
		ContinuousR cr = (ContinuousR) o;
		return value == cr.value;
	}
	
	public double getValue(double precision) {
		return value*precision;
	}

}
