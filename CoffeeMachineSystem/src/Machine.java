
public class Machine {

	
	String name;
	
	public Machine(String name) {
		this.name = name;
	}
	
	public void giveOrder(String name, String[] ingredients, int[] amounts) {
		System.out.println("Making Coffe: " + name);
		if(ingredients != null) {
			System.out.println("Notified barista to add the following: ");
			for(int i = 0; i < ingredients.length; i++) {
				System.out.println(ingredients[i]);
			}
		}
		System.out.println();
	}
	
	public String getName() {
		return name;
	}
	
	
}
