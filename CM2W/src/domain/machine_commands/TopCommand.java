package domain.machine_commands;

import domain.Ingredient;

public class TopCommand implements DrinkCommand {
    private final Ingredient topping;

    public TopCommand(Ingredient topping) {
        this.topping = topping;
    }

    @Override
    public String execute() {
        return "[COMMAND] topping drink with " + topping.toString();
    }
}
