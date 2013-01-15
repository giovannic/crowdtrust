package crowdtrust;

import org.apache.commons.lang3.StringUtils;

public class ContinuousResponse extends Response {

	private int [] values;
	
	public ContinuousResponse(int [] values){
		this.values = values;
	}
	
	public ContinuousResponse(String s){
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
		ContinuousResponse cmr = (ContinuousResponse) o;
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
	
	public int[] getRawValues(){
		return this.values;
	}

}
