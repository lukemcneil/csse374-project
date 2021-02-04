package domain.actions;

import domain.Ingredient;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.TopCommand;

import java.util.List;

public class Top extends Add {

    public Top(Recipe wrapped, Ingredient toTop, boolean isRequired) {
        super(wrapped, toTop, isRequired);
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands =  wrapped.produce();
        commands.add(new TopCommand(ingredient));
        return commands;
    }
}
