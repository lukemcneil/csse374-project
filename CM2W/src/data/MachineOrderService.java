package data;

import domain.Coffee;
import domain.Controller;
import domain.Machine;
import domain.MachineType;

import java.util.Arrays;

/**
 * Responsible for submitting orders to machines (by passing them to the controller in the appropriate format.
 * TODO: Externalize mocking of controller functionality. (Currently Mocked in this class, planned to be mocked externally)
 */
public class MachineOrderService {
    private static final Controller centralController = new Controller();

    /**
     * Accepts a coffee order and submits it to a machine.
     * @param machine the machine to submit the order to.
     * @param coffee the coffee to make.
     * @return the status of the order.
     */
    public static String submitOrder(Machine machine, Coffee coffee) {
        // "Sends" command to coffee maker (via central controller)
        String status = centralController.sendCommandToMachine(machine, coffee);
        if (status.equals("MACHINE-RESPONSE: success")) {
            // Imitate the barista notification on the coffee machine.
            // TODO: Verify this is the correct filtering for barista notification (does programmable ever need it? does
            //  automated ever NOT need it?).
            if (machine.getType() != MachineType.PROGRAMMABLE && coffee.getCondiments() != null) {
                System.out.printf("\tMACHINE DISPLAY \"%s\": Notified barista to add the following customizations: %s\n",
                        machine.getMachineID(), coffee.getCondiments());
            }
            return status;
        } else {
            return "MACHINE-RESPONSE: failure";
        }
    }
}
