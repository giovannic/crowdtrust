package crowdtrust;

public abstract class SubTask
{

	private final int id;
  private final String name;
  private final String question;
  private final Media media;

  public abstract String displayToUser();

  public SubTask( int id, String name, String question, Media media )
  {
    this.id = id;
    this.name = name;
    this.question = question;
    this.media = media;
  }

  public int getId()
  {
		return this.id;
	}

  public String getName()
  {
		return this.name;
	}

  public String getQuestion()
  {
    return this.question;
  }

  public Media getMedia()
  {
    return this.media;
  }

};

