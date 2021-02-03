package domain.actions;

import domain.machine_commands.AddIngredientCommand;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.SteamCommand;

import java.util.List;

public class Steam extends Recipe {
    private Recipe wrapped;

    public Steam() {
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands =  wrapped.produce();
        commands.add(new SteamCommand());
        return commands;
    }
}
