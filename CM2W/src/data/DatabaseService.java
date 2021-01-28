package data;

import domain.Coffee;
import domain.Machine;
import testing.MachineThatCannotMakeCoffee;

import javax.crypto.Mac;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static utils.Utils.getMachineType;

// SQLite usage based on example code at https://github.com/xerial/sqlite-jdbc

public class DatabaseService {
    //TODO: determine static vs instantiated DB Service, esp w/ regards to SQLite connection.
    private static Connection connection = null;

    public static void start() {
        try {
            if (connection == null)
                connection = DriverManager.getConnection("jdbc:sqlite:/home/luke/csse374/csse374-project/CM2W/cm2w.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * (Will) Queries the database for all known coffee options.
     * TODO: Point at DB; Complete.
     * @return an ArrayList of all known coffees.
     */
    public static ArrayList<Coffee> getAllCoffees() {
        ArrayList<Coffee> coffees = new ArrayList<Coffee>();

        try
        {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet drinkTypeRS = statement.executeQuery("SELECT DrinkName, Description FROM DrinkType");
            while(drinkTypeRS.next())
            {
                Coffee coffee = new Coffee(
                        drinkTypeRS.getString("DrinkName"),
                        null,
                        drinkTypeRS.getString("Description"),
                        null    // TODO: should ingredients remain separate from customizations? (ie should
                                             //     we include ingredients in Coffee separately from customizations?
                );

                coffees.add(coffee);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return coffees;
    }

    /**
     * (Will) Queries the database for all machines and their info.
     * @return an ArrayList of all machines & their details.
     * TODO: Point at DB; Complete.
     */
    public static ArrayList<Machine> getAllMachines() {
        ArrayList<Machine> machines = new ArrayList<Machine>();

        try
        {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet machineRS = statement.executeQuery(
                    "SELECT MachineID, Capability, Description, Street_Address, ZIP_code " +
                            "FROM CoffeeMaker " +
                            "JOIN CoffeeMakerCapability " +
                            "ON MachineID = Coffeemaker " +
                            "JOIN Controller " +
                            "ON Controller = ControllerID");
            while(machineRS.next())
            {
                Machine machine = new Machine(
                        machineRS.getInt("MachineID"),
                        getMachineType(machineRS.getString("Capability")),
                        machineRS.getString("Street_Address") + ", " + machineRS.getString("ZIP_code"),
                        machineRS.getString("Description")
                );

                machines.add(machine);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }

        return machines;
    }

    public static ArrayList<Machine> getAllMachinesThatCannotMakeCoffee() {
        ArrayList<Machine> machines = new ArrayList<Machine>();
        try
        {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet machineRS = statement.executeQuery(
                    "SELECT MachineID, Capability, Description, Street_Address, ZIP_code " +
                            "FROM CoffeeMaker " +
                            "JOIN CoffeeMakerCapability " +
                            "ON MachineID = Coffeemaker " +
                            "JOIN Controller " +
                            "ON Controller = ControllerID");
            while(machineRS.next())
            {
                MachineThatCannotMakeCoffee machine = new MachineThatCannotMakeCoffee(
                        machineRS.getInt("MachineID"),
                        getMachineType(machineRS.getString("Capability")),
                        machineRS.getString("Street_Address") + ", " + machineRS.getString("ZIP_code"),
                        machineRS.getString("Description")
                );

                machines.add(machine);
            }
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        return machines;
    }


}
