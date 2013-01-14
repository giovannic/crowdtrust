package crowdtrust;

public class BinaryResponse extends Response {

	private boolean value;
	
	public BinaryResponse(boolean value){
		this.value = value;
	}
	
	public BinaryResponse(String b){
		this.value = Boolean.parseBoolean(b);
	}
	
	@Override
	public String serialise() {
		return Boolean.toString(value);
	}

	public boolean isTrue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		BinaryResponse br = (BinaryResponse) o;
		return value == br.isTrue();
	}

	@Override
	public String toString() {
		return Boolean.toString(value);
	}
}
