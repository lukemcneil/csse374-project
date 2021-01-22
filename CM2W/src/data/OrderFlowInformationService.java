package data;

import domain.Coffee;
import domain.machine_allocation_strategies.MachineAllocationStrategy;

import java.util.ArrayList;

import static domain.machine_allocation_strategies.MachineAllocationController.getStrategies;

/**
 * Provides an API for external applications to query menu, strategy and other system infomration to support the order
 * process.
 */
public class OrderFlowInformationService {

    // Consider Extracting to separate Menu Class.
    public ArrayList<String> getBasicMenu() {
        ArrayList<String> menu = new ArrayList<>();

        for (Coffee c : DatabaseService.getAllCoffees()) {
            menu.add(c.getName());
        }

        return menu;
    }

    // TODO: See Above re extracting strategy methods to separate class.
    public ArrayList<String> getBasicStrategyList() {
        ArrayList<String> strategyNames = new ArrayList<>();

        for (MachineAllocationStrategy s : getStrategies()) {
            strategyNames.add(s.getName());
        }

        return strategyNames;
    }
}
