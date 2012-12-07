package crowdtrust;

import java.lang.Math;
import org.apache.commons.math3.special.Gamma;

public class BinarySubTask extends SubTask {
	
	private double [] p;
	
	BinarySubTask(){
		super();
		//start state
		p = new double [2];
		//false
		p[0] = 0.6f;
		//true
		p[1] = 0.6f;
	}
	
	@Override
	public void estimate(Bee annotator, Response r) {
		try{
			BinaryR response = (BinaryR) r;
			double [] accuracies = annotator.getBinaryaccuracy();
			if(response.isTrue())
			{
				p[1] += mlBeta(accuracies[1]);
				p[0] += mlBeta(accuracies[0]);
			} else {
				p[1] += mlBeta(accuracies[0]);
				p[0] += mlBeta(accuracies[1]);
			}
			
			confidence = 0;
		} catch (Exception e) {
			//incorrect response type
			e.printStackTrace();
		}
	}

	private double mlBeta(double accuracy){
		double start = 0;
		double end = 1;
		double mid;
		while (end - start > 0.0001){
			mid = (start + end) / 2;
			double d = diff(mid, accuracy);
			if (d == 0)
				return mid;
			if (d > 0)
				start = mid;
			else
				end = mid;
		}
		return start;
	}

	private double diff(double x, double accuracy) {
		return Gamma.digamma(1 - accuracy) - Gamma.digamma(accuracy) - Math.log(1 - x) + Math.log(x);
	}
}
