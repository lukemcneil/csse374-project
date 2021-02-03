package domain.actions;
import domain.DrinkCommand;
import domain.Ingredient;

import java.util.List;

public class AddIngredient extends Recipe {
    private Recipe wrapped;

    public AddIngredient(Ingredient ingredient) {

    }

    @Override
    public List<DrinkCommand> produce() {
        // TODO: Add ingredient
        wrapped.produce();
        return null;
    }


}
