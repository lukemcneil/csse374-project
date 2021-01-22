package domain;

import data.DatabaseService;
import domain.machine_allocation_strategy.*;

import java.util.ArrayList;
import java.util.Arrays;

public class CPS {
    // TODO: Consider splitting the strategy list (and strategy selection) into a separate class.
    private static ArrayList<MachineAllocationStrategy> strategies = new ArrayList<>(Arrays.asList(
            new HighestRatedStrategy(), // TODO: There's probably a better way to do this...
            new LeastWaitStrategy(),    //  (probably by allowing the strategies to add themselves to the list instead of keeping this list up to date).
            new NearestStrategy(),
            new RandomStrategy()
    ));

    private static final MachineAllocationStrategy defaultStrategy = new NearestStrategy();

    private static MachineAllocationStrategy getStrategy(String strategy) {
        for (MachineAllocationStrategy s: strategies) {
            if (s.strategyMatchesInput(strategy))
                return s;
        }

        return defaultStrategy;
    }

    public void receiveOrder(String coffeeName, String size, String strategy) {    // TODO: (Coffee) Size as enum instead of string
        MachineAllocationStrategy selectedStrategy = getStrategy(strategy);

        Machine machine = selectedStrategy.selectMachine(new Coffee(coffeeName, null));


        machine.giveOrder(coffeeName, size, null, null);
        // TODO: Deal with ingredients...probably inside Coffee constructor.

//        //int[] regularIngredients = DatabaseSystem.defaultIngredientsForCoffee(coffeeName);
//        int[] regularIngredients = new int[10]; // just for use while the other one is commented out
//
//        if (ingredients != null && ingredients.length > 0) {
//            // We know that it is not part of the simple machine
//
//            // For Milestone 1 Part 1, it will still be sent to a Simple domain.Machine, and the Barista handles condiments
//
//            this.machs.get(0).giveOrder(coffeeName, ingredients, amounts);
//        } else {
//
//            this.machs.get(0).giveOrder(coffeeName, null, null);
//            if (amounts != null && amounts.length > 0) {
//                // we know that there are specific amounts of conds. added
//                // (must be programmable)
//            } else {
//                if (ingredients.length > regularIngredients.length) {
//                    // we know that extra condiments were added
//                } else {
//                    // we know a default coffee was request with no special additives
//
//                    // may not be important information
//                }
//            }
//        }
    }

    // Consider Extracting to separate Menu Class.
    public ArrayList<String> getBasicMenu() {
        ArrayList<String> menu = new ArrayList<>();

        for (Coffee c : DatabaseService.getAllCoffees()) {
            menu.add(c.name);
        }

        return menu;
    }

    // TODO: See Above re extracting strategy methods to separate class.
    public ArrayList<String> getBasicStrategyList() {
        ArrayList<String> strategyNames = new ArrayList<>();

        for (MachineAllocationStrategy s : strategies) {
            strategyNames.add(s.getName());
        }

        return strategyNames;
    }
}
