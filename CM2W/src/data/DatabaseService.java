package data;

import domain.Coffee;
import domain.Machine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

// SQLite implementation based on example code at https://github.com/xerial/sqlite-jdbc

public class DatabaseService {
    //TODO: determine static vs instantiated DB Service, esp w/ regards to SQLite connection.
    private static Connection connection = null;

    public static void start() {
        try {
            if (connection == null)
                connection = DriverManager.getConnection("jdbc:sqlite:cm2w.db");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
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

        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("./src/data/CoffeeList.txt"));
            String line = reader.readLine();
            while (line != null) {
                ArrayList<String> vals = new ArrayList<String>();
                int start = 0;
                int end = 0;
                while (start < line.length()) {
                    if (end == line.length() || line.charAt(end) == ' ') {
                        String s = line.substring(start, end);
                        start = end + 1;
                        end++;
                        vals.add(s);
                    } else {
                        end++;
                    }
                }

                String[] ingredients = new String[vals.size() - 1];
                for (int i = 0; i < ingredients.length; i++) {
                    ingredients[i] = vals.get(i + 1);
                }

                Coffee newCoff = new Coffee(vals.get(0),null,  ingredients);
                coffees.add(newCoff);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
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

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("./src/data/MachineList.txt"));
            String line = reader.readLine();
            while (line != null) {
                Machine machine = new Machine(0, null, line, null);
                machines.add(machine);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return machines;
    }


}
