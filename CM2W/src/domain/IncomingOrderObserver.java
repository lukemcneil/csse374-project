package domain;

public interface IncomingOrderObserver {
    int receiveOrder(Coffee coffee, String strategy);
}
