package domain;

import java.util.Arrays;

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

    public void make(Coffee coffee) {
        System.out.printf("INFO: Machine\"%s\" (%s) is Making a %s", machineID, location, coffee);
        if (coffee.getCustomizations() != null) {    // TODO: Determine machine type and act accordingly.
            System.out.printf("MACHINE DISPLAY \"%s\": Notified barista to add the following customizations: \n", Arrays.toString(coffee.getCustomizations()));
        }
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
}
