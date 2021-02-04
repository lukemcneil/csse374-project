package domain.actions;

import domain.Ingredient;
import domain.machine_commands.DrinkCommand;
import domain.machine_commands.SteamCommand;

import java.util.List;

public class Steam extends Add {

    public Steam(Recipe wrapped, Ingredient toSteam, boolean isRequired) {
        super(wrapped, toSteam, isRequired);
    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands = wrapped.produce();
        commands.add(new SteamCommand());
        return commands;
    }
}
