package crowdtrust;

public class BinaryR extends Response {

	private boolean value;
	
	public BinaryR(boolean value){
		this.value = value;
	}
	
	public BinaryR(String b){
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
		BinaryR br = (BinaryR) o;
		return value == br.isTrue();
	}

}
