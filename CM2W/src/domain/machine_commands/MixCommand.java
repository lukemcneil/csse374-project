package domain.machine_commands;

public class MixCommand implements DrinkCommand{
	@Override
	public String execute() {
		return "[COMMAND] mixing drink";
	}
}
