package finance.domains;

public class BankAccount {
    private int id;

    private String name;

    private double balance;

    public BankAccount(int id, String name) {
        this.id = id;
        this.name = name;
        balance = 0;
    }
}
