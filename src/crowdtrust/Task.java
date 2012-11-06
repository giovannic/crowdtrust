package crowdtrust;

public abstract class Task
{

  public enum Type
  {
    BinaryImageClassification;
  };

  private int id;
  private String name;

  public Task(String name)
  {
    this.name = name;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  protected void addToDatabase()
  {
    // need to be able to find out which user is logged in
    // get a connection, call serializeAdditionalData, insert into database
  }

  protected abstract BLOB serializeAdditionalData();

  public abstract void assignCrowd();

  // Define implementations of general crowd assignment methods, to be used by
  // assignCrowd.

};
