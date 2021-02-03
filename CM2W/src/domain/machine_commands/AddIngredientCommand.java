package domain.machine_commands;

import domain.Ingredient;

public class AddIngredientCommand implements DrinkCommand{
	private Ingredient ingredient;

	public AddIngredientCommand(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public void execute() {
		System.out.println("[COMMAND] adding " + ingredient.toString() + " to drink");
	}
}
