package parallelScripts;

import org.testng.annotations.Test;

public class NewTest3 {
	 @Test
	  public void testOne() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestOne from Sample Three "+id);
	  }
	  @Test
	  public void testSecond() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestSecond from Sample Three "+id);
	  }
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestThree from Sample Three "+id);
	  }
	  @Test
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestFour from Sample Three "+id);
	  }
}
