package finance.domains;

import lombok.Getter;

public class BankAccount {
    @Getter
    private int id;

    private String name;

    private double balance;

    public BankAccount(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Bank account " + name +
                "\nid: " + id +
                "\nbalance: " + balance;
    }
}
