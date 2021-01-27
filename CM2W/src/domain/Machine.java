package domain;

import data.MachineOrderService;

public class Machine {


    private final int machineID;
    private final MachineType type;
    private final String location;
    private final String description;
    private int waitTime;
    // TODO: any additional attributes from DB schema.

    public Machine(int machineID, MachineType type, String location, String description) {
        this.machineID = machineID;
        this.type = type;
        this.location = location;
        this.description = description;
    }

    public Machine(int machineID, MachineType type, String location, String description, int waitTime) {
        this.machineID = machineID;
        this.type = type;
        this.location = location;
        this.description = description;
        this.waitTime = waitTime;
    }

    public String make(Coffee coffee) {
        // TODO: Verify Machine can make specific coffee
        return MachineOrderService.submitOrder(this, coffee);
    }

    public boolean canMake(Coffee coffee) {
        // TODO: Complete.
//        return false;
//        return Math.random() < .5;
        return true;
    }

    public int getMachineID() {
        return machineID;
    }

    public MachineType getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Machine{" +
                "machineID=" + machineID +
                ", type=" + type +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", waitTime=" + waitTime +
                '}';
    }
}
