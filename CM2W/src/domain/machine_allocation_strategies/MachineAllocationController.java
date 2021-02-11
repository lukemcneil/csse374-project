package domain.machine_allocation_strategies;


import tests.MachineThatCannotMakeCoffeeStrategy;
import tests.NoMachineStrategy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Maintains the list of machine allocation strategies and determines the correct strategy for a given selction.
 */
public class MachineAllocationController {
    // TODO: Consider splitting the strategy list (and strategy selection) into a separate class.
    private static final ArrayList<MachineAllocationStrategy> strategies = new ArrayList<>(Arrays.asList(
            new HighestRatedStrategy(), // TODO: There's probably a better way to do this...
            new LeastWaitStrategy(),    //  (probably by allowing the strategies to add themselves to the list instead of keeping this list up to date).
            new NearestStrategy(),
            new RandomStrategy(),
            new MachineThatCannotMakeCoffeeStrategy(),
            new NoMachineStrategy()
    ));

    private static final MachineAllocationStrategy defaultStrategy = new NearestStrategy();

    public static MachineAllocationStrategy getStrategy(String strategy) {
        for (MachineAllocationStrategy s : strategies) {
            if (s.strategyMatchesInput(strategy))
                return s;
        }

        return defaultStrategy;
    }

    public static void registerAllocationStrategy(MachineAllocationStrategy strategy) {
        strategies.add(strategy);
    }

    public static ArrayList<MachineAllocationStrategy> getStrategies() {
        return strategies;
    }
}
