package crowdtrust;

import org.apache.commons.lang3.StringUtils;

public class ContinuousR extends Response {

	private int [] values;
	
	public ContinuousR(int [] values){
		this.values = values;
	}
	
	public ContinuousR(String s){
		String [] split = s.split("/");
		this.values = new int [split.length];
		for (int i = 0; i < split.length; i++){
			values[i] = Integer.parseInt(split[i]);
		}
	}
	
	@Override
	public String serialise() {
		return StringUtils.join(values);
	}

	@Override
	public boolean equals(Object o) {
		ContinuousR cmr = (ContinuousR) o;
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
