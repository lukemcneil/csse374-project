package presentation;// Condiment <-- just details
// Coffee <-- Contains a list of condiments
// Order <-- Name, domain.Machine Location, Coffee, Condiments
// domain.CPS <-- Figures out where the order goes
// domain.Controller <-- Just prints out status information
// domain.Machine <-- Would hold data for which set the coffee would work for

import data.IncomingOrderService;
import data.OrderFlowInformationService;
import domain.Condiment;
import utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import static utils.Utils.isValidSize;

public class MockApp {
    IncomingOrderService orderService;
    OrderFlowInformationService infoService;

    /**
     * Initializes an "interactive" console app to mock order submission.
     *
     * @param orderService the IncomingOrderAPI to direct the request to
     * @param infoService  the OrderFlowInformationService to direct information requests to (ex for menu and
     *                     strategy options)
     */
    public MockApp(IncomingOrderService orderService, OrderFlowInformationService infoService) {
        this.orderService = orderService;
        this.infoService = infoService;
    }

    //	static ArrayList<Coffee> coffees = new ArrayList<Coffee>();

    /**
     * Runs the basic mock app. Presents basic menu, then submits selected coffee to
     * specified CPS system. Reports order status back to user via console messages.
     */
    public void run() {

        Scanner in = new Scanner(System.in);


        while (true) {
            Condiment[] condiments = {new Condiment("Cream", "Dairy based individual serving"), new Condiment("Sugar", "Case sugar teaspoon"), new Condiment("NutraSweet", "Individual serving")};
            ArrayList<Condiment> addedCondiments = new ArrayList<>();
            ArrayList<String> menu = infoService.getBasicMenu();
            System.out.printf("Hi, welcome to Flunkin's! Our menu is (type exit to exit): %s\n", menu.toString());
            System.out.print("Selection: ");
            String coffeeName = in.nextLine();
            if (coffeeName.equalsIgnoreCase("exit")) {
                return;
            } else if (menu.contains(coffeeName)) {
                System.out.print("Please select a size (S, M, L): ");
                String size = in.nextLine();
                if (isValidSize(size)) {
                    System.out.printf("Please select a machine selection strategy from %s:", infoService.getBasicStrategyList());
                    String strategy = in.nextLine();
                    for(Condiment condiment : condiments) {
                        System.out.println("Would you like "+condiment.getName() + "? (Y or N)");
                        String answer = in.nextLine();
                        if (answer.equalsIgnoreCase("Y")) {
                            addedCondiments.add(condiment);
                        }
                    }
                    System.out.printf("Placing an order for a %s %s with %s from a/the %s machine. \n", size, coffeeName, addedCondiments, strategy); // TODO: return strategy for this response
                    int orderNumber = orderService.placeOrder(coffeeName, size, strategy); // TODO: support customization of ingredients, consider copying menu option & returning with modified ingredients list and added size.
                    if (orderNumber < 0) {
                        Utils.printError(orderNumber);
                    } else {
                        System.out.printf("Order placed. Your order number is %d\n", orderNumber);
                    }
                    // TODO: Any additional returned status???
                } else {
                    System.out.printf("Invalid size \"%s\" selected. Order not placed.\n", size);
                }
            } else {
                System.out.printf("Coffee \"%s\" is not available. Order not placed.\n", coffeeName);
            }


//			boolean flag = false;
//			for(int i = 0; i < coffees.size(); i++) {
//				if(coffees.get(i).getName().toLowerCase().equals(coffeeName.toLowerCase())) {
//					flag = true;
//					orderService.receiveOrder(coffees.get(i).getName(), coffees.get(i).getConds(), null);
//				}
//			}
        }

    }

}
