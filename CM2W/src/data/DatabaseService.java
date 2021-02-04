package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Coffee;
import domain.Condiment;
import domain.Ingredient;
import domain.Machine;
import domain.actions.*;
import testing.MachineThatCannotMakeCoffee;

import static utils.Utils.getMachineType;

// SQLite usage based on example code at https://github.com/xerial/sqlite-jdbc

/**
 * Provides database access for the CPS system. Enables querying coffee, machine, and condiment information.
 */
public class DatabaseService {
    //TODO: Implement Singleton Design pattern.
    private static Connection connection = null;

    /**
     * Opens the database connection.
     */
    public static void start() {
        try {
            if (connection == null)
                connection = DriverManager.getConnection("jdbc:sqlite:cm2w.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Closes the database connection.
     */
    public static void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Queries the database for all known coffee options.
     * @return an ArrayList of all known coffees.
     */
    public static ArrayList<Coffee> getAllCoffees() {
        ArrayList<Coffee> coffees = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet drinkTypeRS = statement.executeQuery("SELECT DrinkName, Description FROM DrinkType");
            while (drinkTypeRS.next()) {
                Coffee coffee = new Coffee(
                        drinkTypeRS.getString("DrinkName"),
                        null,
                        drinkTypeRS.getString("Description"),
                        null
                );

                coffees.add(coffee);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return coffees;
    }

    /**
     * Queries the database for all machines and their info.
     *
     * @return an ArrayList of all machines & their details.
     */
    public static ArrayList<Machine> getAllMachines() {
        ArrayList<Machine> machines = new ArrayList<Machine>();

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet machineRS = statement.executeQuery(
                    "SELECT MachineID, Capability, Description, Street_Address, ZIP_code " +
                            "FROM CoffeeMaker " +
                            "JOIN CoffeeMakerCapability " +
                            "ON MachineID = Coffeemaker " +
                            "JOIN Controller " +
                            "ON Controller = ControllerID");
            while (machineRS.next()) {
                Machine machine = new Machine(
                        machineRS.getInt("MachineID"),
                        getMachineType(machineRS.getString("Capability")),
                        machineRS.getString("Street_Address") + ", " + machineRS.getString("ZIP_code"),
                        machineRS.getString("Description")
                );

                machines.add(machine);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return machines;
    }

    /**
     * Queries the database for machines that are capable of making a specific coffee
     *
     * @return an ArrayList of all machines & their details.
     */
    public static ArrayList<Machine> getAllMachinesThatCanMake(Coffee coffee) {
        ArrayList<Machine> machines = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet machineRS = statement.executeQuery(
                    "SELECT MachineID, Capability, Description, Street_Address, ZIP_code " +
                            "FROM CoffeeMaker CM " +
                            "JOIN CoffeeMakerCapability CMC " +
                            "ON MachineID = CMC.Coffeemaker " +
                            "JOIN Controller " +
                            "ON Controller = ControllerID " +
                            "JOIN CoffeeMakerDrink CMD " +
                            "ON CM.MachineID = CMD.Coffeemaker " +
                            "WHERE CMD.Drinktype = '" + coffee.getName() + "'");   // TODO: Prepared statement (to avoid SQL injection)
            while (machineRS.next()) {
                Machine machine = new Machine(
                        machineRS.getInt("MachineID"),
                        getMachineType(machineRS.getString("Capability")),
                        machineRS.getString("Street_Address") + ", " + machineRS.getString("ZIP_code"),
                        machineRS.getString("Description")
                );

                machines.add(machine);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return machines;
    }

    //TODO: What does this method do? Do we need it?
    public static ArrayList<Machine> getAllMachinesThatCannotMakeCoffee() {
        ArrayList<Machine> machines = new ArrayList<Machine>();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet machineRS = statement.executeQuery(
                    "SELECT MachineID, Capability, Description, Street_Address, ZIP_code " +
                            "FROM CoffeeMaker " +
                            "JOIN CoffeeMakerCapability " +
                            "ON MachineID = Coffeemaker " +
                            "JOIN Controller " +
                            "ON Controller = ControllerID");
            while (machineRS.next()) {
                MachineThatCannotMakeCoffee machine = new MachineThatCannotMakeCoffee(
                        machineRS.getInt("MachineID"),
                        getMachineType(machineRS.getString("Capability")),
                        machineRS.getString("Street_Address") + ", " + machineRS.getString("ZIP_code"),
                        machineRS.getString("Description")
                );

                machines.add(machine);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return machines;
    }

    /**
     * Factory Method to produce decorated Recipe object based on recipe stored in DB.
     * @param coffeeName the name of the coffee to build a recipe for.
     * @return the coffee's recipe, or null if no recipe exists (meaning a simple or automated machine may make the coffee).
     */
    public static Recipe getRecipe(String coffeeName) {
        Recipe recipe = new Recipe();
        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet recipeRS = statement.executeQuery(
                    "SELECT IngredientName, IsRequired, Quantity, Step, Operation, Description " +
                            "FROM DrinkRecipe " +
                            "LEFT OUTER JOIN Ingredient " +
                            "ON IngredientName = Name " +
                            "WHERE DrinkName = '" + coffeeName + "' " +  // TODO: Prepared statement (to avoid SQL injection)
                            "ORDER BY Step"
            );

            // No Recipe, so return null.
            if (!recipeRS.next()) {
                return null;
            }

            // Has Recipe, so Decorate
            do {
                String operation = recipeRS.getString("Operation");
                switch (operation) {
                    case "Add":
                        recipe = new Add(
                                recipe,
                                new Ingredient(
                                        recipeRS.getString("IngredientName"),
                                        recipeRS.getString("Description"),
                                        recipeRS.getInt("Quantity")
                                ),
                                1 == recipeRS.getInt("IsRequired"));
                        break;
                    case "Top":
                        recipe = new Top(
                                recipe,
                                new Ingredient(
                                    recipeRS.getString("IngredientName"),
                                    recipeRS.getString("Description"),
                                    recipeRS.getInt("Quantity")
                                ),
                                1 == recipeRS.getInt("IsRequired"));
                        break;
                    case "Mix":
                        recipe = new Mix(recipe, 1 == recipeRS.getInt("IsRequired"));
                        break;
                    case "Steam":
                        recipe = new Steam(
                                recipe,
                                new Ingredient(
                                    recipeRS.getString("IngredientName"),
                                    recipeRS.getString("Description"),
                                    recipeRS.getInt("Quantity")
                                ),
                                1 == recipeRS.getInt("IsRequired"));
                        break;
                    default:
                        throw new Error("Recipe Operation " + operation + " not recognized");
                }
            } while (recipeRS.next());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return recipe;
    }

    /**
     * Returns all condiments available.
     * @return a list of all available condiments.
     */
    public static List<Condiment> getAllCondiments() {
        ArrayList<Condiment> condiments = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            ResultSet condimentRS = statement.executeQuery(
                    "SELECT Name, Description FROM Condiment");
            while (condimentRS.next()) {
                condiments.add(new Condiment(
                        condimentRS.getString("Name"),
                        condimentRS.getString("Description")
                ));

            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return condiments;
    }
}
