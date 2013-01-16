package crowdtrust;

public class Account {

	private int id;
	private String name;

	public Account(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public String getName(){
		return this.name;
	}
	
}
