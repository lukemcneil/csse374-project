package domain.machine_allocation_strategy;

import data.DatabaseService;
import domain.Coffee;
import domain.Machine;

import java.util.ArrayList;

public class LeastWaitStrategy implements MachineAllocationStrategy {
    private static final String strategyName = "Least Wait";

    @Override
    public Machine selectMachine(Coffee coffee) {
        ArrayList<Machine> machines = DatabaseService.getAllMachines();
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
