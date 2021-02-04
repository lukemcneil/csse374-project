package domain.actions;

import domain.Ingredient;
import domain.machine_commands.AddIngredientCommand;
import domain.machine_commands.DrinkCommand;

import java.util.List;

public class Add extends Recipe {
     Recipe wrapped;
     final Ingredient ingredient;
     final boolean isRequired;

    public Add(Recipe wrapped, Ingredient ingredient, boolean isRequired) {
        this.ingredient = ingredient;
        this.wrapped = wrapped;
        this.isRequired = isRequired;
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands = wrapped.produce();
        commands.add(new AddIngredientCommand(ingredient));
        return commands;
    }

    public Boolean isIngredient() {
        return true;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public Boolean isRequired() {
        return isRequired;
    }

    public Recipe getWrapped() {
        return wrapped;
    }

    @Override
    public String toString() {
        return super.toString() + ": " +  ingredient;
    }

    public void setWrapped(Recipe newWrapped) {
        wrapped = newWrapped;
    }
}
