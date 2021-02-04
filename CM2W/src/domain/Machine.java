package domain;

import data.MachineOrderService;
import domain.machine_commands.DrinkCommand;

import java.util.List;

public class Machine {
    protected final int machineID;
    protected final MachineType type;
    protected final String location;
    protected final String description;
    protected int waitTime;
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
        if (!canMake(coffee)) {
            throw new MachineTypeMismatchError(this.toString() + " cannot make " + coffee.toString());
        }

        List<DrinkCommand> commands = coffee.produce();

        if (commands == null) {
            System.out.println("Coffee has no recipe. Using basic machine mode.");
        } else {
            for (DrinkCommand command : commands) {
                System.out.println(command.execute());
            }
        }

        return MachineOrderService.submitOrder(this, coffee);
    }

    public boolean canMake(Coffee coffee) {
        return (type == MachineType.PROGRAMMABLE || !coffee.offersCustomization());
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
