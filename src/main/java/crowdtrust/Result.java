package crowdtrust;

public class Result {
	private SubTask subtask;
	private Response r;
	
	public Result(SubTask subtask, Response r){
		this.subtask = subtask;
		this.r = r;
	}
	
	public SubTask getSubtask() {
		return subtask;
	}
	public void setSubtask(SubTask subtask) {
		this.subtask = subtask;
	}
	public Response getR() {
		return r;
	}
	public void setR(Response r) {
		this.r = r;
	}
}
