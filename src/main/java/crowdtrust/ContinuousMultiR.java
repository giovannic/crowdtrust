package crowdtrust;

public class ContinuousMultiR extends Response {

	private int [] values;
	
	@Override
	Byte[] serialise() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		ContinuousMultiR cmr = (ContinuousMultiR) o;
		for (int i = 0; i < values.length; i++){
			 if (cmr.values[i] != values[i])
				 return false;
		}
		return true;
	}
	
	public double [] getValues(double precision){
		double [] t = new double [values.length];
		for (int i = 0; i < values.length; i++){
			t[i] = values[i] * precision;
		}
		return t;
	}

}
