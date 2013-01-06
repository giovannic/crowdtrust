package crowdtrust;

public class ContinuousMultiR extends Response {

	private int [] values;
	
	public ContinuousMultiR(int [] values){
		this.values = values;
	}
	
	public ContinuousMultiR(byte [] b){
		String s = new String(b);
		String [] split = s.split("/");
		this.values = new int [split.length];
		for (int i = 0; i < split.length; i++){
			values[i] = Integer.parseInt(split[i]);
		}
	}
	
	@Override
	public byte[] serialise() {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < values.length; i++){
			s.append(values[i] + "/");
		}
		return s.toString().getBytes();
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
