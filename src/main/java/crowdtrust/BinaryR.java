package crowdtrust;

public class BinaryR extends Response {

	private boolean value;
	
	public BinaryR(boolean value){
		this.value = value;
	}
	
	public BinaryR(byte [] b){
		this.value = Boolean.parseBoolean(new String(b));
	}
	
	@Override
	byte[] serialise() {
		return Boolean.toString(value).getBytes();
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
