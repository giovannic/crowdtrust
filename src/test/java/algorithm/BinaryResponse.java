package algorithm;

public class BinaryResponse extends AnnotatorResponse {
	protected int answer;
	
	public BinaryResponse(int answer){
		this.answer = answer;
	}
	
	public int getAnswer(){
		return this.answer;
	}
}
