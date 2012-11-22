package crowdtrust;

public class LabelImageClassificationSubTask extends SubTask
{

  private String label;

  public String display()
  {

  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public String getResult()
  {
    return this.label;
  }

}
