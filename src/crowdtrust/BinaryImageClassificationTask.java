package crowdtrust;

import java.util.List;

public class BinaryImageClassificationTask extends Task
{

  private String question;
  private List<Image> images;

  // what class should images be stored as?!
  public BinaryImageClassificationTask(String name, String question, List<Image> images)
  {
    super(name);
    this.question = question;
    this.images = images;

    this.addToDatabase();
  }

  // need a method to reverse this?
  // possibly a Task constructor which takes (id, name and BLOB) and instantiates the appropriate subclass
  protected BLOB serializeAdditionalData()
  {
    // serialize question and images
  }

  public void assignCrowd()
  {
    // 
  }

  // inner class extending taskdata

};
