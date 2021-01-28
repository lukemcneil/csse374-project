package testing;

import data.DatabaseService;
import domain.Coffee;
import domain.Machine;
import domain.machine_allocation_strategies.MachineAllocationStrategy;

import java.util.ArrayList;

public class MachineThatCannotMakeCoffeeStrategy implements MachineAllocationStrategy {
	private static final String strategyName = "CannotMakeCoffee";

	@Override
	public Machine selectMachine(Coffee coffee) {
		ArrayList<Machine> machines = DatabaseService.getAllMachinesThatCannotMakeCoffee();
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
