package crowdtrust;

public class BinaryR extends Response {

	private boolean value;
	
	BinaryR(boolean value){
		super();
		this.setValue(value);
	}
	
	@Override
	Byte [] serialise() {
		//TODO
		return null;
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
