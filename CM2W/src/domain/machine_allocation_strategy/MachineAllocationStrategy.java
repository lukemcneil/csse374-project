package domain.machine_allocation_strategy;

import domain.Coffee;
import domain.Machine;

public interface MachineAllocationStrategy {
    Machine selectMachine(Coffee coffee);

    boolean strategyMatchesInput(String strategy);

    String getName();
}
