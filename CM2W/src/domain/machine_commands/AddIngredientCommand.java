package domain.machine_commands;

import domain.Ingredient;

public class AddIngredientCommand implements DrinkCommand{
	private Ingredient ingredient;

	public AddIngredientCommand(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Override
	public String execute() {
		return "[COMMAND] adding " + ingredient.toString() + " to drink";
	}
}
