package domain;

import data.DatabaseService;
import domain.actions.Recipe;
import domain.machine_commands.DrinkCommand;

import java.util.List;

public class Coffee {

    private final String name;
    private final Size size;
    private final String description;
    private Recipe recipe;
    private List<Condiment> condiments;

    public Coffee(String name, Size size, String description, List<Condiment> condiments) {
        this.name = name;
        this.size = size;
        this.description = description;
        this.condiments = condiments;
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

    public List<Condiment> getCondiments() {
        return condiments;
    }

    public void setCondiments(List<Condiment> condiments) {
        this.condiments = condiments;
    }

    @Override
    public String toString() {
        return String.format("%s %s, with customizations: %s", size, name, condiments);
    }

    public Recipe getRecipe() {
        // Lazy load recipe
        if (recipe == null) {
            this.recipe = DatabaseService.getRecipe(name);
        }


        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<DrinkCommand> produce() {
        if (offersCustomization()) {
            return recipe.produce();
        } else {
            return null;
        }
    }

    public boolean hasCondiments() {
        return condiments != null;
    }

    public boolean offersCustomization() {
        return getRecipe() != null;
    }
}
