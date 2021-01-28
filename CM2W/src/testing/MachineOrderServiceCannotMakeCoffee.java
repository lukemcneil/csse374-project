package testing;

import domain.Coffee;
import domain.Controller;
import domain.Machine;
import domain.MachineType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Responsible for submitting orders to machines (by passing them to the controller in the appropriate format.
 * TODO: Externalize mocking of controller functionality. (Currently Mocked in this class, will be mocked externally)
 */
public class MachineOrderServiceCannotMakeCoffee {

	private static Controller centralController = new ControllerThatCannotMakeCoffee();

	public static String submitOrder(Machine machine, Coffee coffee) {
		// "Sends" command to coffee maker (via central controller)
		String status = centralController.sendCommandToMachine(machine, coffee);
		if (status.equals("MACHINE-RESPONSE: success")) {
			// Imitate the barista notification on the coffee machine.
			// TODO: Verify this is the correct filtering for barista notification (does programmable ever need it? does
			//  automated ever NOT need it?).
			if (machine.getType() != MachineType.PROGRAMMABLE && coffee.getCustomizations() != null) {
				System.out.printf("\tMACHINE DISPLAY \"%s\": Notified barista to add the following customizations: %s\n", machine.getMachineID(),
						Arrays.toString(coffee.getCustomizations()));
			}
			return status;
		}
		else {
			return "MACHINE-RESPONSE: failure";
		}
	}
}