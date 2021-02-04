package data;

import domain.Coffee;
import domain.Condiment;
import domain.machine_allocation_strategies.MachineAllocationStrategy;

import java.util.ArrayList;
import java.util.List;

import static domain.machine_allocation_strategies.MachineAllocationController.getStrategies;

/**
 * Provides an API for external applications to query menu, strategy and other system information to support the order
 * process.
 */
public class OrderFlowInformationService {

    /**
     * Returns a list of Coffee names as strings.
     * @return an arraylist of available coffee names.
     */
    public ArrayList<String> getBasicMenu() {
        ArrayList<String> menu = new ArrayList<>();

        for (Coffee c : DatabaseService.getAllCoffees()) {
            menu.add(c.getName());
        }

        return menu;
    }

    /**
     * Returns a list of all available coffees as Coffee objects.
     * @return an arraylist of available coffees.
     */
    public ArrayList<Coffee> getAdvancedMenu() {
        return DatabaseService.getAllCoffees();
    }

    /**
     * Returns a list of all available machine selection strategy names.
     * @return the list of strategy names.
     */
    public ArrayList<String> getBasicStrategyList() {
        ArrayList<String> strategyNames = new ArrayList<>();

        for (MachineAllocationStrategy s : getStrategies()) {
            strategyNames.add(s.getName());
        }

        return strategyNames;
    }

    /**
     * Returns a list of all condiments that can be added to a specific coffee.
     * Note: this is not currently coffee specific.
     * @param coffee the coffee to determine valid condiments for.
     * @return t list of condiments available.
     */
    public List<Condiment> getAvailableCondiments(Coffee coffee) {
        return DatabaseService.getAllCondiments();
    }
}
