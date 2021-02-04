package testing;

import domain.Coffee;
import domain.Machine;
import domain.machine_allocation_strategies.MachineAllocationStrategy;

public class NoMachineStrategy implements MachineAllocationStrategy {
    private static final String strategyName = "NoMachine";

    @Override
    public Machine selectMachine(Coffee coffee) {
//		ArrayList<Machine> machines = DatabaseService.getAllMachines();
        return null;
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
