package domain.machine_allocation_strategies;

public class NoMachinesFoundError extends Error {
    public NoMachinesFoundError(String message) {
        super(message);
    }
}
