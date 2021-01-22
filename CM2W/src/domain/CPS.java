package domain;

import domain.machine_allocation_strategies.MachineAllocationStrategy;

import static domain.machine_allocation_strategies.MachineAllocationController.getStrategy;

public class CPS implements IncomingOrderObserver {


    public void receiveOrder(String coffeeName, Size size, String strategy) {    // TODO: (Coffee) Size as enum instead of string
        MachineAllocationStrategy selectedStrategy = getStrategy(strategy);

        Coffee coffee = new Coffee(coffeeName, size, null);   // TODO: Implement customizations
        Machine machine = selectedStrategy.selectMachine(coffee);

        machine.make(coffee);
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
}
