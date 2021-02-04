package domain;

import domain.machine_allocation_strategies.MachineAllocationStrategy;

import static domain.machine_allocation_strategies.MachineAllocationController.getStrategy;

public class CPS implements IncomingOrderObserver {

    private int currentOrderID = 0;

    public int receiveOrder(Coffee coffee, String strategy) {
        MachineAllocationStrategy selectedStrategy = getStrategy(strategy);

        Machine machine = selectedStrategy.selectMachine(coffee);

        String result;
        if (machine != null) {
            result = machine.make(coffee);
            if (result.equals("MACHINE-RESPONSE: failure")) {
                return -1;
            } else {
                return currentOrderID++;
            }
        } else
            return -2;
    }
}
