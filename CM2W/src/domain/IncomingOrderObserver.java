package domain;

public interface IncomingOrderObserver {
    int receiveOrder(String coffeeName, Size size, String strategy);
}
