package crowdtrust;

import java.io.UnsupportedEncodingException;

public class MultiValueResponse extends Response{
	
	int selection;

	public MultiValueResponse(int selection){
		this.selection = selection;
	}
	
	public MultiValueResponse(String b) throws NumberFormatException, UnsupportedEncodingException{
		this.selection = Integer.parseInt(b);
	}
	
	@Override
	public String serialise() {
		return Integer.toString(selection);
	}

	@Override
	public String toString() {
		return "" + selection;
	}
	
	public int getSelection(){
		return this.selection;
	}

	@Override
	public boolean equals(Object o) {
		MultiValueResponse mv = (MultiValueResponse) o;
		return selection == mv.selection;
	}

}
