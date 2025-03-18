package finance.domains;

import java.util.Date;

public class Operation {
    private int id;

    private char type;

    private double amount;

    private Date date;

    private BankAccount bankAccount;

    private Category category;

    private String description;

    public Operation(int id, char type, double amount, Date date, BankAccount bankAccount, Category category) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.bankAccount = bankAccount;
        this.category = category;
    }
}
