package domain.machine_commands;

public class SteamCommand implements DrinkCommand {
    @Override
    public String execute() {
        return "[COMMAND] steaming drink";
    }
}
