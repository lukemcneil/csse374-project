package tests;

import domain.Coffee;
import domain.Controller;
import domain.Machine;

public class ControllerThatCannotMakeCoffee extends Controller {
    public String sendCommandToMachine(Machine machine, Coffee coffee) {
        return "MACHINE-RESPONSE: failure";
    }
}
