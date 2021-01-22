package domain;

public class Coffee {

    String name;
    String[] conds;

    public Coffee(String name, String[] condiments) {
        this.name = name;
        this.conds = condiments;
    }

    public String getName() {
        return this.name;
    }

    public String[] getConds() {
        return this.conds;
    }

}
