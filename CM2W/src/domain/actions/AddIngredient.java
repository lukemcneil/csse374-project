package domain.actions;
import domain.Ingredient;
import domain.machine_commands.AddIngredientCommand;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.MixCommand;

import java.util.List;

public class AddIngredient extends Recipe {
    private Recipe wrapped;
    private Ingredient ingredient;

    public AddIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands =  wrapped.produce();
        commands.add(new AddIngredientCommand(ingredient));
        return commands;
    }


}
