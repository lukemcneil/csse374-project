package domain.actions;

import domain.machine_commands.DrinkCommand;
import domain.machine_commands.MixCommand;

import java.util.ArrayList;
import java.util.List;

public class Mix extends Recipe {
    private Recipe wrapped;

    public Mix() {

    }

    @Override
    public List<DrinkCommand> produce() {
        List<DrinkCommand> commands =  wrapped.produce();
        commands.add(new MixCommand());
        return commands;
    }
}
