package domain.actions;

import domain.Ingredient;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.InsertCupCommand;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public List<DrinkCommand> produce() {
        ArrayList<DrinkCommand> commands = new ArrayList<>();
        commands.add(new InsertCupCommand());
        return commands;
    }

    public Boolean isIngredient() {
        return null;
    }

    public Ingredient getIngredient() {
        return null;
    }

    public Boolean isRequired() {
        return null;
    }

    public Recipe getWrapped() {
        return null;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public void setWrapped(Recipe recipeRecursiveHelper) {
        throw new Error("Base Recipe does not wrap anything");
    }
}
