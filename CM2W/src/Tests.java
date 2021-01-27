import static org.junit.Assert.*;

import data.DatabaseService;
import data.IncomingOrderService;
import domain.CPS;
import org.junit.Test;


public class Tests {

	@Test
	public void testTest()
	{
		DatabaseService.start();
		IncomingOrderService orderService = new IncomingOrderService();
		orderService.registerObserver(new CPS());
		int orderNumber = orderService.placeOrder("Latte", "S", "HighestRated");
		assertEquals(0, orderNumber);
		orderNumber = orderService.placeOrder("Americano", "S", "HighestRated");
		assertEquals(1, orderNumber);
	}

}
