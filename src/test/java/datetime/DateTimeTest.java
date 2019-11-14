package datetime;



import java.util.Date;

import org.joda.time.Duration;
import org.junit.Test;

public class DateTimeTest {

	@Test
	public void testDateTime() {
		try {
			Date d1 = new Date();
			System.out.println(d1);
			Thread.sleep(5000);
			Date d2 = new Date();
			System.out.println(d2);
			
			System.out.println(d1.after(d2));
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
