package presentation;
// Condiment <-- just details
// Coffee <-- Contains a list of condiments
// Order <-- Name, domain.Machine Location, Coffee, Condiments
// domain.CPS <-- Figures out where the order goes
// domain.Controller <-- Just prints out status information
// domain.Machine <-- Would hold data for which set the coffee would work for

import data.IncomingOrderService;
import data.OrderFlowInformationService;
import domain.Coffee;
import domain.Condiment;
import domain.actions.Recipe;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MockApp {
    private IncomingOrderService orderService;
    private OrderFlowInformationService infoService;
    private final Scanner in;

    /**
     * Initializes an "interactive" console app to mock order submission.
     *
     * @param orderService the IncomingOrderAPI to direct the request to
     * @param infoService  the OrderFlowInformationService to direct information requests to (for menu and
     *                     strategy options)
     */
    public MockApp(IncomingOrderService orderService, OrderFlowInformationService infoService) {
        this.orderService = orderService;
        this.infoService = infoService;
        this.in = new Scanner(System.in);
    }

    /**
     * Runs the basic mock app. Presents basic menu, then submits selected coffee to
     * the CPS system. Reports order status back to user via console messages.
     */
    public void run() {
        while (true) {
            try {
                Coffee coffee = menuPrompt();
                if (coffee == null) return; // requested exit

                sizePrompt(coffee);
                condimentsPrompt(coffee);
                customizeRecipePrompt(coffee);
                String strategy = strategyPrompt();

                System.out.printf("Placing an order for a %s from the %s machine. \n", coffee, strategy);

                int orderNumber = orderService.placeOrder(coffee, strategy);
                if (orderNumber < 0) {
                    throw new CoffeeOrderingError(Utils.getErrorMessage(orderNumber));
                } else {
                    System.out.printf("Order placed. Your order number is %d\n", orderNumber);
                }

            } catch (Error e) {
                System.out.printf("Order was not placed because %s\n", e.getMessage());
            }
        }
    }

    /**
     * Prompts the user to select a coffee from the menu and returns it.
     * @return the selected coffee, or null if the user wishes to exit.
     */
    private Coffee menuPrompt() {
        System.out.printf("Hi, welcome to Flunkin's! Our menu is (type exit to exit): %s\n", infoService.getBasicMenu());
        System.out.print("Selection: ");
        String selection = in.nextLine();

        if (selection.equalsIgnoreCase("exit")) return null;

        for (Coffee c: infoService.getAdvancedMenu()) {
            if (selection.equalsIgnoreCase(c.getName())) {
                return c;
            }
        }

        throw new CoffeeOrderingError("Coffee '" + selection + "' not found");
    }

    /**
     * Prompts the user to select a size.
     * @param coffee the coffee to offer sizes for (and to set size for).
     */
    private void sizePrompt(Coffee coffee) {
        System.out.printf("Please select a size (S, M, L) for your %s: ", coffee.getName());
        String size = in.nextLine();
    }

    /**
     * Prompts the user to customize included condiments.
     * @param coffee the coffee to customize condiments for.
     */
    private void condimentsPrompt(Coffee coffee) {
        ArrayList<Condiment> condiments = new ArrayList<>();

        System.out.printf("Would you like to add condiments to your %s (Y or N)?", coffee.getName());

        String selection = in.nextLine();
        switch(selection.toLowerCase()) {
            default:
                System.out.printf("Invalid response %s. Assuming you meant no.", selection);
            case "n": case "no":
                coffee.setCondiments(null);
                break;
            case "y": case"yes":
                for (Condiment condiment : infoService.getAvailableCondiments(coffee)) {
                    System.out.printf("Would you like %s? (Y or N): ", condiment.getName());
                    String answer = in.nextLine();
                    if (answer.equalsIgnoreCase("Y")) {
                        condiments.add(condiment);
                    }
                }
                coffee.setCondiments(condiments);
                break;
        }
    }

    /**
     * Prompts the user to customize the coffee recipe.
     * @param coffee the coffee to modify.
     */
    private void customizeRecipePrompt(Coffee coffee) {
        if (!coffee.offersCustomization()) return;

        System.out.printf("Would you like to customize your %s? (Y or N)", coffee.getName());

        String selection = in.nextLine();
        switch(selection.toLowerCase()) {
            default:
                System.out.printf("Invalid response %s. Assuming you meant no.", selection);
            case "n":
            case "no":
                break;
            case "y":
            case "yes":
                Recipe baseRecipe = coffee.getRecipe();
                Recipe updatedRecipe = recipeRecursiveHelper(baseRecipe);

                coffee.setRecipe(updatedRecipe);
        }

        // TODO: Complete
    }

    private Recipe recipeRecursiveHelper(Recipe recipe) {
        if (recipe.getClass() == Recipe.class) {
            return recipe;
        } else {
            if (recipe.isRequired()) { // Is required. don't offer choice.
                System.out.printf("Keeping required step of %s\n", recipe);
                recipe.setWrapped(recipeRecursiveHelper(recipe.getWrapped()));
                return recipe;
            } else {    // Not required. Give option to remove.
                System.out.printf("Would you like %s? (Y or N) ", recipe);
                if (in.nextLine().equalsIgnoreCase("Y")) {
                    if (recipe.isIngredient())  {
                        System.out.printf("How much %s? ", recipe);
                        int qty = Integer.parseInt(in.nextLine());
                        recipe.getIngredient().setQuantity(qty);
                    }
                    recipe.setWrapped(recipeRecursiveHelper(recipe.getWrapped()));
                    return recipe;
                } else {    // doesn't want, so remove step.
                    return recipeRecursiveHelper(recipe.getWrapped());
                }
            }
        }
    }

    /**
     * Prompts the user to pick an algorithm to select a coffee machine.
     * @return the selected strategy name.
     */
    private String strategyPrompt() {
        List<String> strategies = infoService.getBasicStrategyList();
        System.out.printf("Please select a machine selection strategy from %s:", strategies);
        String strategy = in.nextLine();

        if (strategies.contains(strategy)) {
            return strategy;
        } else {
            throw new CoffeeOrderingError("Invalid machine selection strategy " + strategy);
        }

    }
}
