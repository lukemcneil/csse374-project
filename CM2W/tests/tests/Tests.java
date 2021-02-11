package tests;

import data.DatabaseService;
import data.IncomingOrderService;
import domain.*;
import domain.actions.Add;
import domain.actions.Mix;
import domain.actions.Recipe;
import domain.actions.Top;
import domain.machine_commands.DrinkCommand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class Tests {

    @Test
    public void testUC1PrimaryFlowAndObserverPattern() {
        DatabaseService.start();
        IncomingOrderService orderService = new IncomingOrderService();
        orderService.registerObserver(new CPS());
	    int orderNumber = orderService.placeOrder(new Coffee("Latte", Size.MEDIUM, "none", null), "HighestRated");
        assertEquals(0, orderNumber);
	    orderNumber = orderService.placeOrder(new Coffee("Americano", Size.LARGE, "none", null), "HighestRated");
        assertEquals(1, orderNumber);
    }

    @Test
    public void testStrategyPattern() {
        DatabaseService.start();
        IncomingOrderService orderService = new IncomingOrderService();
        orderService.registerObserver(new CPS());
        int orderNumber = orderService.placeOrder(new Coffee("Latte", Size.MEDIUM, "none", null), "HighestRated");
        assertEquals(0, orderNumber);
        orderNumber = orderService.placeOrder(new Coffee("Latte", Size.MEDIUM, "none", null), "LeastWait");
        assertEquals(1, orderNumber);
        orderNumber = orderService.placeOrder(new Coffee("Latte", Size.MEDIUM, "none", null), "Nearest");
        assertEquals(2, orderNumber);
    }

    @Test
    public void testUC1ExceptionFlow() {
        DatabaseService.start();
        IncomingOrderService orderService = new IncomingOrderService();
        orderService.registerObserver(new CPS());
        int orderNumber = orderService.placeOrder(new Coffee("Latte", Size.MEDIUM, "none", null), "CannotMakeCoffee");
        assertEquals(-1, orderNumber);
    }

    @Test
    public void testUC1AlternateFlow() {
        DatabaseService.start();
        IncomingOrderService orderService = new IncomingOrderService();
        orderService.registerObserver(new CPS());
        int orderNumber = orderService.placeOrder(new Coffee("Latte", Size.MEDIUM, "none", null), "NoMachine");
        assertEquals(-2, orderNumber);
    }

    @Test
    public void testDecorator() {
        Coffee coffee = new Coffee("latte", Size.MEDIUM, "description", new ArrayList<>());
        coffee.setRecipe(new Top(new Add(new Mix(new Recipe(), true), new Ingredient("milk", "description"), true), new Ingredient("whipped cream", "none"), true));
        List<DrinkCommand> commmands = coffee.getRecipe().produce();
        String result = "";
        for (DrinkCommand c : commmands) {
            result += c.execute();
        }
        assertEquals("[COMMAND] inserting new cup[COMMAND] mixing drink[COMMAND] adding milk to drink[COMMAND] topping drink with whipped cream", result);
    }
}
