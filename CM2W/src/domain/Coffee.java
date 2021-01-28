package domain;

import java.util.Arrays;

public class Coffee {

    private final String name;
    private final Size size;
    private final String description;

    private Condiment[] addedCondiments;

//    private final String[] customizations;    // TODO: Better datatype for customizations, probably object/array of objects.

    public Coffee(String name, Size size, String description, Condiment[] addedCondiments) {
        this.name = name;
        this.size = size;
        this.description = description;
        this.addedCondiments = addedCondiments;
//        this.customizations = customizations;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Size getSize() {
        return size;
    }

    public Condiment[] getAddedCondiments() {
        return addedCondiments;
    }

    @Override
    public String toString() {
        return String.format("%s %s, with customizations: %s", size, name, Arrays.toString(addedCondiments));
    }
}
