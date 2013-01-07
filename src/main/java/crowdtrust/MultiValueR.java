package crowdtrust;

import java.io.UnsupportedEncodingException;

public class MultiValueR extends Response{
	
	int selection;

	public MultiValueR(int selection){
		this.selection = selection;
	}
	
	public MultiValueR(String b) throws NumberFormatException, UnsupportedEncodingException{
		this.selection = Integer.parseInt(b);
	}
	
	@Override
	public String serialise() {
		return Integer.toString(selection);
	}

	@Override
	public boolean equals(Object o) {
		MultiValueR mv = (MultiValueR) o;
		return selection == mv.selection;
	}

}
