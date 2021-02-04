package domain;

import domain.actions.Recipe;

import java.util.Arrays;

public class Coffee {

    private final String name;
    private final Size size;
    private final String description;
    private Recipe recipe;
    private final Condiment[] addedCondiments;

    public Coffee(String name, Size size, String description, Condiment[] addedCondiments) {
        this.name = name;
        this.size = size;
        this.description = description;
        this.addedCondiments = addedCondiments;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public Condiment[] getAddedCondiments() {
        return addedCondiments;
    }

    @Override
    public String toString() {
        return String.format("%s %s, with customizations: %s", size, name, Arrays.toString(addedCondiments));
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
