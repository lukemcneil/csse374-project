package domain.actions;

import domain.DrinkCommand;

import java.util.List;

public class Top extends Recipe {
    private Recipe wrapped;

    public Top() {
    }

    @Override
    public List<DrinkCommand> produce() {
        // TODO: Steam
        wrapped.produce();
        return null;
    }
}
