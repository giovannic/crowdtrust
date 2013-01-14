package db;

import junit.framework.TestCase;

public class TestAddNullSubTask extends TestCase {

  private final int task_id = 1;
  private final String filename = null;

  public void testAddNullSubtask() {
    assertFalse(SubTaskDb.addSubtask(filename, task_id));
  }

}
