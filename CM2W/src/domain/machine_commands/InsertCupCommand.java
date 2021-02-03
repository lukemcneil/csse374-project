package domain.machine_commands;

public class InsertCupCommand implements DrinkCommand {
	@Override
	public void execute() {
		System.out.println("[COMMAND] pouring coffee into cup");
	}
}
