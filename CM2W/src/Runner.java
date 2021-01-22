import data.IncomingOrderService;
import data.OrderFlowInformationService;
import domain.CPS;
import presentation.MockApp;

public class Runner {
    public static void main(String[] args) {
        // Start DB
        System.out.print("Starting Database...");
        data.DatabaseService.start();
        System.out.println("Done.");

        // Run Mock App
        OrderFlowInformationService infoService = new OrderFlowInformationService();
        IncomingOrderService orderService = new IncomingOrderService();

        CPS cps = new CPS();
        orderService.registerObserver(cps);

        MockApp app1 = new MockApp(orderService, infoService);

        app1.run();

        // Close DB
        System.out.print("Closing Database...");
        data.DatabaseService.close();
        System.out.println("Done.");
    }
}
