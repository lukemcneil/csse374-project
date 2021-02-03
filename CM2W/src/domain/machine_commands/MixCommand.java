package domain.machine_commands;

public class MixCommand implements DrinkCommand{
	@Override
	public void execute() {
		System.out.println("[COMMAND] mixing drink");
	}
}
