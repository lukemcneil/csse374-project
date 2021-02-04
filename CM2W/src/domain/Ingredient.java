package domain;

public class Ingredient {
    private final String name;
    private final String description;
    private Integer quantity;

    public Ingredient(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Ingredient(String name, String description, Integer quantity) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name;
    }
}
