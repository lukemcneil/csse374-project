package testing;
import domain.Coffee;
import domain.Machine;
import domain.MachineType;


public class MachineThatCannotMakeCoffee extends Machine {

	public MachineThatCannotMakeCoffee(int machineID, MachineType type, String location, String description) {
		super(machineID, type, location, description);
	}

	public MachineThatCannotMakeCoffee(int machineID, MachineType type, String location, String description, int waitTime) {
		super(machineID, type, location, description, waitTime);
	}

	public String make(Coffee coffee) {
		// TODO: Verify Machine can make specific coffee
		return MachineOrderServiceCannotMakeCoffee.submitOrder(this, coffee);
	}

	public boolean canMake(Coffee coffee) {
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
