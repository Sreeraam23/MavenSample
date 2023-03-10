package parallelScripts;

import org.testng.annotations.Test;

public class NewTest2 {
  @Test(groups="feature1")
  public void testOne() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestOne from Sample Second "+id);
  }
  @Test(groups="feature1")
  public void testSecond() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestSecond from Sample Second "+id);
  }
  @Test(groups="feature2")
  public void testThree() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestThree from Sample Second "+id);
  }
  @Test(groups="feature2")
  public void testFour() {
	  long id = Thread.currentThread().getId();
	  System.out.println("TestFour from Sample Second "+id);
  }
  
}
