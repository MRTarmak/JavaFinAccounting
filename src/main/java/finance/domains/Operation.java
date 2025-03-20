package finance.domains;

import lombok.Getter;

import java.time.LocalDate;

public class Operation {
    @Getter
    private int id;

    private int type;

    private double amount;

    private LocalDate date;

    private int bankAccountId;

    private int categoryId;

    private String description;

    private Operation(OperationBuilder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.amount = builder.amount;
        this.date = builder.date;
        this.bankAccountId = builder.bankAccountId;
        this.categoryId = builder.categoryId;
        this.description = builder.description;
    }

    public static class OperationBuilder {
        private int id;

        private int type;

        private double amount;

        private LocalDate date;

        private int bankAccountId;

        private int categoryId;

        private String description;

        public OperationBuilder(int id, int type, double amount) {
            this.id = id;
            this.type = type;
            this.amount = amount;
        }

        public OperationBuilder date(LocalDate date) {
            this.date = date;

            return this;
        }

        public OperationBuilder bankAccountId(int bankAccountId) {
            this.bankAccountId = bankAccountId;

            return this;
        }

        public OperationBuilder categoryId(int categoryId) {
            this.categoryId = categoryId;

            return this;
        }

        public OperationBuilder description(String description) {
            this.description = description;

            return this;
        }

        public Operation build() {
            return new Operation(this);
        }
    }

    public String toString() {
        return "Operation " + id +
                "\ntype: " + (type == 0 ? "Income" : "Expenses") +
                "\namount: " + amount +
                "\ndate: " + date +
                "\naccountId: " + bankAccountId +
                "\ncategoryId: " + categoryId +
                "\ndescription: " + description;
    }
}
