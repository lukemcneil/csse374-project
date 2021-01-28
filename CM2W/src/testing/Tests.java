package testing;

import static org.junit.Assert.*;

import data.DatabaseService;
import data.IncomingOrderService;
import domain.CPS;
import org.junit.Test;

public class Tests {

	@Test
	public void testUC1PrimaryFlowAndObserverPattern()
	{
		DatabaseService.start();
		IncomingOrderService orderService = new IncomingOrderService();
		orderService.registerObserver(new CPS());
		int orderNumber = orderService.placeOrder("Latte", "S", "HighestRated");
		assertEquals(0, orderNumber);
		orderNumber = orderService.placeOrder("Americano", "L", "HighestRated");
		assertEquals(1, orderNumber);
	}

	@Test
	public void testStrategyPattern()
	{
		DatabaseService.start();
		IncomingOrderService orderService = new IncomingOrderService();
		orderService.registerObserver(new CPS());
		int orderNumber = orderService.placeOrder("Latte", "S", "HighestRated");
		assertEquals(0, orderNumber);
		orderNumber = orderService.placeOrder("Latte", "S", "Random");
		assertEquals(1, orderNumber);
		orderNumber = orderService.placeOrder("Latte", "S", "LeastWait");
		assertEquals(2, orderNumber);
		orderNumber = orderService.placeOrder("Latte", "S", "Nearest");
		assertEquals(3, orderNumber);
	}

	@Test
	public void testUC1ExceptionFlow()
	{
		DatabaseService.start();
		IncomingOrderService orderService = new IncomingOrderService();
		orderService.registerObserver(new CPS());
		int orderNumber = orderService.placeOrder("Latte", "S", "CannotMakeCoffee");
		assertEquals(-1, orderNumber);
	}

	@Test
	public void testUC1AlternateFlow()
	{
		DatabaseService.start();
		IncomingOrderService orderService = new IncomingOrderService();
		orderService.registerObserver(new CPS());
		int orderNumber = orderService.placeOrder("Latte", "S", "NoMachine");
		assertEquals(-2, orderNumber);
	}
}

