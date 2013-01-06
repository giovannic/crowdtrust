package crowdtrust;

import java.io.UnsupportedEncodingException;

public class MultiValueR extends Response{
	
	int selection;

	public MultiValueR(int selection){
		this.selection = selection;
	}
	
	public MultiValueR(byte [] b) throws NumberFormatException, UnsupportedEncodingException{
		this.selection = Integer.parseInt(new String(b, "UTF-8"));
	}
	
	@Override
	byte[] serialise() {
		return Integer.toString(selection).getBytes();
	}

	@Override
	public boolean equals(Object o) {
		MultiValueR mv = (MultiValueR) o;
		return selection == mv.selection;
	}

}
