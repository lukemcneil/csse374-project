package domain.machine_commands;

import domain.Ingredient;

public class TopCommand implements DrinkCommand {
	private Ingredient topping;

	public TopCommand(Ingredient topping) {
		this.topping = topping;
	}

	@Override
	public void execute() {
		System.out.println("[COMMAND] topping drink with " + topping.toString());
	}
}
