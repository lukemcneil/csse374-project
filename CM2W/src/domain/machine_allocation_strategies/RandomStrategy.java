/**
 * Strategy that selects a random machine that is capable of fulfilling the coffee order request.
 */
package domain.machine_allocation_strategies;

import data.DatabaseService;
import domain.Coffee;
import domain.Machine;

import java.util.ArrayList;

public class RandomStrategy implements MachineAllocationStrategy {
    private static final String strategyName = "Random";

    @Override
    public Machine selectMachine(Coffee coffee) {
        ArrayList<Machine> machines = DatabaseService.getAllMachinesThatCanMake(coffee);
        System.out.printf("[RANDOM STRATEGY] Selecting a random machine from %d machines capable of making a %s\n", machines.size(), coffee);

        int selection = (int) (Math.random() * machines.size());

        return machines.get(selection);
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
