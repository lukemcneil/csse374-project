package domain;

public class Machine {


    String name;

    public Machine(String name) {
        this.name = name;
    }

    public void giveOrder(String name, String size, String[] ingredients, int[] amounts) {    // TODO: Create Ingredients class/bundle ingredient with amount instead of parallel arrays.
        System.out.printf("INFO: Machine\"%s\" is Making a %s %s, with ingredients: \n\t\t%s\n\t\tand amounts: \n\t\t%s\n", this.name, name, size, ingredients, amounts);
        if (ingredients != null) {    // TODO: Determine machine type and act accordingly.
            System.out.printf("MACHINE DISPLAY \"%s\": Notified barista to add the following: \n", name);
            for (int i = 0; i < ingredients.length; i++) {
                System.out.println("\t\t" + ingredients[i]);
            }
        }
    }

    public String getName() {
        return name;
    }


}
