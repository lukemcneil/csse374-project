package data;

import domain.IncomingOrderObserver;
import domain.Size;

import java.util.ArrayList;

import static utils.Utils.getSize;

/**
 * Provides an API for accepting incoming order requests and distributing them to all internal registered observers (via
 * IncomingOrderObserverInterface).
 */
public class IncomingOrderService {
    private final ArrayList<IncomingOrderObserver> observers = new ArrayList<>();

    public void registerObserver(IncomingOrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IncomingOrderObserver observer) {
        observers.remove(observer);
    }

    public int placeOrder(String coffeeName, String sizeStr, String strategy) {
        int orderID = -3;
        for (IncomingOrderObserver observer : observers) {
            Size size = getSize(sizeStr);
            orderID = observer.receiveOrder(coffeeName, size, strategy);
        }
        return orderID; // TODO: return order number, probably from a specific observer originally from DB insert??)
    }
}
