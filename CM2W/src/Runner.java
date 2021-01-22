import domain.CPS;
import presentation.MockApp;

public class Runner {
    public static void main(String[] args) {
        // Start DB
        System.out.print("Starting Database...");
        data.DatabaseService.start();
        System.out.println("Done.");
        // Run Mock App
        CPS cps = new CPS();
        MockApp app1 = new MockApp();
        app1.run(cps);
        // Close DB
        System.out.print("Closing Database...");
        data.DatabaseService.close();
        System.out.println("Done.");
    }
}
