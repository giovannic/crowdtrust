package crowdtrust;

public abstract class Media {
		
		//data store
		public byte [] data;
		
		//template methods
	    public abstract void HTMLShow();
	    public abstract void Load(byte [] data);
}
