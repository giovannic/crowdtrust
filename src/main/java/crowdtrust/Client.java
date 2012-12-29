package crowdtrust;

import java.util.List;

public class Client {
	private int id;
	private String name;
	private String password;
	private List<Task> tasks;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
