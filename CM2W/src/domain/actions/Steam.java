package domain.actions;

import domain.DrinkCommand;

import java.util.List;

public class Steam extends Recipe {
    private Recipe wrapped;

    public Steam() {
    }

    @Override
    public List<DrinkCommand> produce() {
        // TODO: Steam
        wrapped.produce();
        return null;
    }
}
