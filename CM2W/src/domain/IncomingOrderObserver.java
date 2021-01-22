package domain;

public interface IncomingOrderObserver {
    void receiveOrder(String coffeeName, Size size, String strategy);
}
