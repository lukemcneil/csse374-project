package domain.actions;

import domain.DrinkCommand;
import domain.Ingredient;

import java.util.List;

public class Mix extends Recipe {
    private Recipe wrapped;

    public Mix() {

    }

    @Override
    public List<DrinkCommand> produce() {
        // TODO: Mix drink
        wrapped.produce();
        return null;
    }
}
