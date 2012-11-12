package crowdtrust;

import java.util.Collection;
import java.util.List;

public class BinaryTask extends Task
{

  public String question;
  private List<SubTask> subtasks;

  public BinaryTask()
  {
	  super("placeholder");
  }
  
  // what class should images be stored as?!
  public BinaryTask(String name, String question)
  {
    super(name);
    this.question = question;
    this.addToDatabase();
  }

  public boolean assignSubtask(SubTask s)
  {
	  return subtasks.add(s);
  }
  
  public boolean assignSubtasks(Collection<SubTask> s)
  {
	  return subtasks.addAll(s);
  }

  // need a method to reverse this?
  // possibly a Task constructor which takes (id, name and BLOB) and instantiates the appropriate subclass
  /*
  protected BLOB serializeAdditionalData()
  {
    // serialize question and images
  }
  */

  public void assignCrowd()
  {
    // 
  }

  // inner class extending taskdata

};
