package data;

import domain.Coffee;
import domain.IncomingOrderObserver;

import java.util.ArrayList;

/**
 * Provides an API for accepting incoming order requests and distributing them to all internal registered observers (via
 * IncomingOrderObserverInterface).
 * Follows the Observer Design Pattern to notify CPS and a (possible) future order logger.
 */
public class IncomingOrderService {
    private final ArrayList<IncomingOrderObserver> observers = new ArrayList<>();

    /**
     * Registers an object (that implements the IncomingOrderObserver interface) to receive messages for incoming orders.
     * @param observer the object to register.
     */
    public void registerObserver(IncomingOrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Stops an object from receiving messages about incoming orders.
     * @param observer the object to remove.
     */
    public void removeObserver(IncomingOrderObserver observer) {
        observers.remove(observer);
    }

    /**
     * Primary order interface for third party apps.
     * @param coffee the coffee that is being ordered.
     * @param strategy name of the machine allocation strategy to use.
     * @return positive order number, or an error code (in case of error).
     */
    public int placeOrder(Coffee coffee, String strategy) {
        int orderID = -3;
        for (IncomingOrderObserver observer : observers) {
            orderID = observer.receiveOrder(coffee, strategy);
        }
        return orderID;
    }
}
