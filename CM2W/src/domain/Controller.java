package domain;

public class Controller {

    public String sendCommandToMachine(Machine machine, Coffee coffee) {
//		return String.format("\tINFO: Machine %s is Making a %s\n", machine, coffee);
        if (machine.canMake(coffee))
            return "MACHINE-RESPONSE: success";
        else
            return "MACHINE-RESPONSE: failure";
    }
}
