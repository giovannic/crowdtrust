package crowdtrust;

public class SubTask {
	
	public enum Media{
		IMAGE_LINK(){

			@Override
			public void HTMLShow() {
				// TODO Auto-generated method stub
				
			}
			
		},
		
		IMAGE_RAW(){

			@Override
			public void HTMLShow() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		//data store
		private byte [] data;
		
		//template methods
	     public abstract void HTMLShow();
	};
	
	private Media media;
}
