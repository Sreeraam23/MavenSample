package parallelScripts;

import org.testng.annotations.Test;

public class NewTest4 {
	@Test
	  public void testOne() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestOne from Sample Four "+id);
	  }
	  @Test
	  public void testSecond() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestSecond from Sample Four "+id);
	  }
	  @Test
	  public void testThree() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestThree from Sample Four "+id);
	  }
	  @Test(threadPoolSize = 3,invocationCount = 8, timeOut=1000)
	  public void testFour() {
		  long id = Thread.currentThread().getId();
		  System.out.println("TestFour from Sample Four "+id);
	  }
}
