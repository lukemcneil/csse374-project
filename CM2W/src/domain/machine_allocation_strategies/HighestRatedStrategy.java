package domain.machine_allocation_strategies;

import data.DatabaseService;
import domain.Coffee;
import domain.Machine;

import java.util.ArrayList;

public class HighestRatedStrategy implements MachineAllocationStrategy {
    private static final String strategyName = "HighestRated";

    @Override
    public Machine selectMachine(Coffee coffee) {
        ArrayList<Machine> machines = DatabaseService.getAllMachinesThatCanMake(coffee);
        // TODO: Complete
        return machines.get(0);
    }

    @Override
    public boolean strategyMatchesInput(String strategy) {
        return strategy.equalsIgnoreCase(strategyName);
    }

    @Override
    public String getName() {
        return strategyName;
    }
}
