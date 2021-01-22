package domain;

import data.DatabaseService;
import domain.Machine;

import java.util.ArrayList;

public class CPS {
    public CPS() {
    }


    public void receiveOrder(String coffeeName, String size) {    // TODO: Size as enum instead of string
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

        ArrayList<Machine> machines = DatabaseService.getAllMachines();

        machines.get(0).giveOrder(coffeeName, size, null, null);
        // TODO: Pass ingredients...
        // TODO: Select Machine via strategy pattern
        // TODO: Literally the entire method...
    }

    public ArrayList<String> getBasicMenu() {
        ArrayList<String> menu = new ArrayList<>();

        for (Coffee c : DatabaseService.getAllCoffees()) {
            menu.add(c.name);
        }

        return menu;
    }


}
