package domain.machine_commands;

public class SteamCommand implements DrinkCommand {
	@Override
	public void execute() {
		System.out.println("[COMMAND] steaming drink");
	}
}
