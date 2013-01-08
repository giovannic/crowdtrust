package db;

import crowdtrust.Response;

public class Result {
	public int sId;
	public Response r;
	
	public Result(int s, Response r){
		sId = s;
		this.r = r;
	}
}
