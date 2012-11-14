package crowdtrust;

public class ImageRaw extends Media{

		@Override
		public void HTMLShow() {
			// img src ...
		}

		@Override
		public void Load(byte [] data) {
			//push data to db
			this.data = data;
		}
}
