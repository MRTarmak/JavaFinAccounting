package finance.domains;

import lombok.Getter;

public class Category {
    @Getter
    private int id;

    @Getter
    private int type;

    private String name;

    public Category(int id, int type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category " + name +
                "\nid: " + id +
                "\ntype: " + (type == 0 ? "Income" : "Expenses");
    }
}
