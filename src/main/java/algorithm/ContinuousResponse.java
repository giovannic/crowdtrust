package algorithm;

public class ContinuousResponse extends AnnotatorResponse {
	protected double[] answer;
	
	public ContinuousResponse(double[] answer){
		this.answer = answer;
	}
	
	public double[] getAnswer(){
		return this.answer;
	}
}
