package domain.actions;

import domain.Ingredient;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.MixCommand;

import java.util.List;

public class Mix extends Recipe {
    private Recipe wrapped;
    private final boolean isRequired;

    public Mix(Recipe wrapped, boolean isRequired) {
        this.wrapped = wrapped;
        this.isRequired = isRequired;
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands = wrapped.produce();
        commands.add(new MixCommand());
        return commands;
    }

    public Boolean isIngredient() {
        return false;
    }

    public Ingredient getIngredient() {
        return null;
    }

    public Boolean isRequired() {
        return isRequired;
    }

    public Recipe getWrapped() {
        return wrapped;
    }

    public void setWrapped(Recipe newWrapped) {
        wrapped = newWrapped;
    }

}
