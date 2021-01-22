package presentation;// Condiment <-- just details
// Coffee <-- Contains a list of condiments
// Order <-- Name, domain.Machine Location, Coffee, Condiments
// domain.CPS <-- Figures out where the order goes
// domain.Controller <-- Just prints out status information
// domain.Machine <-- Would hold data for which set the coffee would work for

import domain.CPS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static utils.Utils.isValidSize;

public class MockApp {

//	static ArrayList<Coffee> coffees = new ArrayList<Coffee>();

    /**
     * An "interactive" console app to mock order submission. Presents basic menu, then submits selected coffee to
     * specified CPS system. Reports order status back to user via console messages.
     *
     * @param cps the CPS system to direct the request to
     */
    public void run(CPS cps) {

//		getCoffeeList();

//		CPS cps = new CPS(getMachineList());

        Scanner in = new Scanner(System.in);

        while (true) {
            ArrayList<String> menu = cps.getBasicMenu();
            System.out.printf("Hi, welcome to Flunkin's! Our menu is: %s\n", menu.toString());
            System.out.print("Selection: ");
            String coffeeName = in.nextLine();
            if (coffeeName.equalsIgnoreCase("exit")) {
                return;
            } else if (menu.contains(coffeeName)) {
                System.out.print("Please select a size (S, M, L): ");
                String size = in.nextLine();
                if (isValidSize(size)) {
                    System.out.printf("Placing an order for a %s %s. \n", size, coffeeName);
                    cps.receiveOrder(coffeeName, size);        // TODO: support customization of ingredients
                    System.out.printf("Order placed. Your order number is %d\n", 0);  // TODO: Order Number
                    // TODO: Any additional returned status???
                } else {
                    System.out.printf("Invalid size \"%s\" selected. Order not placed.\n", size);
                }
            } else {
                System.out.printf("Coffee \"%s\" is not available. Order not placed.\n", coffeeName);
            }


//			System.out.println();
//			boolean flag = false;
//			for(int i = 0; i < coffees.size(); i++) {
//				if(coffees.get(i).getName().toLowerCase().equals(coffeeName.toLowerCase())) {
//					flag = true;
//					cps.receiveOrder(coffees.get(i).getName(), coffees.get(i).getConds(), null);
//				}
//			}
//
//			if(!flag) {
//				System.out.println("Could not find the Coffee Name in the System...\n");
//			}

        }

    }

}
