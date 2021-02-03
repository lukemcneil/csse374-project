package domain.actions;

import domain.machine_commands.DrinkCommand;
import domain.machine_commands.InsertCupCommand;

import java.util.ArrayList;
import java.util.List;

public class Recipe {
    public List<DrinkCommand> produce() {
        ArrayList<DrinkCommand> commands = new ArrayList<>();
        commands.add(new InsertCupCommand());
        return commands;
    }
}
