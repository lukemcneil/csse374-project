package domain.actions;

import domain.Ingredient;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.TopCommand;

import java.util.List;

public class Top extends Recipe {
    private Recipe wrapped;
    private Ingredient ingredient;

    public Top(Recipe wrapped, Ingredient ingredient) {
        this.wrapped = wrapped;
        this.ingredient = ingredient;
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands =  wrapped.produce();
        commands.add(new TopCommand(ingredient));
        return commands;
    }
}
