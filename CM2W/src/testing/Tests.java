package testing;

import static org.junit.Assert.*;

import data.DatabaseService;
import data.IncomingOrderService;
import domain.*;
import domain.actions.AddIngredient;
import domain.actions.Mix;
import domain.actions.Recipe;
import domain.actions.Top;
import domain.machine_commands.DrinkCommand;
import org.junit.Test;

import java.util.List;

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

	@Test
	public void testDecorator()
	{
		Coffee coffee = new Coffee("latte", Size.MEDIUM, "description", new Condiment[]{});
		coffee.setRecipe(new Top(new AddIngredient(new Mix(new Recipe()), new Ingredient("milk", "description")), new Ingredient("whipped cream", "none")));
	    List<DrinkCommand> commmands = coffee.getRecipe().produce();
	    String result = "";
	    for (DrinkCommand c : commmands) {
	    	result += c.execute();
	    }
	    assertEquals("[COMMAND] pouring coffee into cup[COMMAND] mixing drink[COMMAND] adding milk to drink[COMMAND] topping drink with whipped cream", result);
	}
}

