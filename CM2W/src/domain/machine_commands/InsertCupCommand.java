package domain.machine_commands;

public class InsertCupCommand implements DrinkCommand {
    @Override
    public String execute() {
        return "[COMMAND] inserting new cup";
    }
}
