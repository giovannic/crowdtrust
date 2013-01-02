package algorithm;

public class MultiResponse extends AnnotatorResponse {
	protected int answer;
	
	public MultiResponse(int answer){
		this.answer = answer;
	}
	
	public int getAnswer(){
		return this.answer;
	}
}
