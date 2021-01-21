import presentation.Main;

public class Runner {
    public static void main(String[] args) {
        // Start DB
            System.out.print("Starting Database...");
            data.DatabaseService.start();
            System.out.println("Done.");
        // Run App
            Main.main(args);
        // Close DB
            System.out.print("Closing Database...");
            data.DatabaseService.close();
            System.out.println("Done.");
    }
}
