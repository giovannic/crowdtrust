package crowdtrust;

public class Result {
	private SubTask subtask;
	private Estimate e;
	
	public Result(SubTask subtask, Estimate r){
		this.subtask = subtask;
		this.e = r;
	}
	
	public SubTask getSubtask() {
		return subtask;
	}
	public void setSubtask(SubTask subtask) {
		this.subtask = subtask;
	}
	public Estimate getE() {
		return e;
	}
	public void setE(Estimate r) {
		this.e = r;
	}
}
