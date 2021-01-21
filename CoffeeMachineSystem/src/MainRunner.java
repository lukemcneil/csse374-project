import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainRunner {

	static ArrayList<Coffee> coffees = new ArrayList<Coffee>();
	
	public static void main(String[] args) {
		
		getCoffeeList();
		
		// Condiment <-- just details
		// Coffee <-- Contains a list of condiments
		// Order <-- Name, Machine Location, Coffee, Condiments
		// CPS <-- Figures out where the order goes
		// Controller <-- Just prints out status information
		// Machine <-- Would hold data for which set the coffee would work for
		
		CPS cps = new CPS(getMachineList());
		
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.print("Order: ");
			String line = s.nextLine();
			System.out.println();
			boolean flag = false;
			for(int i = 0; i < coffees.size(); i++) {
				if(coffees.get(i).getName().toLowerCase().equals(line.toLowerCase())) {
					flag = true;
					cps.ReceiveOrder(coffees.get(i).getName(), coffees.get(i).getConds(), null);
				}
			}
			
			if(!flag) {
				System.out.println("Could not find the Coffee Name in the System...\n");
			}
			
		}
		
	}
	
	private static void getCoffeeList() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src\\CoffeeList.txt"));
			String line = reader.readLine();
			while (line != null) {
				ArrayList<String> vals = new ArrayList<String>();
				int start = 0;
				int end = 0;
				while(start < line.length()) {
					if(end == line.length() || line.charAt(end) == ' ') {
						String s = line.substring(start, end);
						start = end + 1;
						end++;
						vals.add(s);
					} else {
						end++;
					}
				}
				
				//for(int i = 0; i < vals.size(); i++) {
					//System.out.print(vals.get(i) + " ");
				//}
				
				String[] ingrds = new String[vals.size() - 1];
				for(int i = 0; i < ingrds.length; i++) {
					ingrds[i] = vals.get(i + 1);
				}
				
				Coffee newCoff = new Coffee(vals.get(0), ingrds);
				coffees.add(newCoff);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Coffees:");
		for(int i = 0; i < coffees.size(); i++) {
			System.out.println(coffees.get(i).getName());
		}
		System.out.println();
		
	}
	
	private static ArrayList<Machine> getMachineList() {
		
		ArrayList<Machine> machines = new ArrayList<Machine>();
		
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("src\\MachineList.txt"));
			String line = reader.readLine();
			while (line != null) {
				Machine newMach = new Machine(line);
				machines.add(newMach);
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
