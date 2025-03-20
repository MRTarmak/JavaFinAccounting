package finance.utilities;

import finance.commands.BankAccount.CreateAccountCommand;
import finance.commands.BankAccount.DeleteAccountCommand;
import finance.commands.BankAccount.GetAccountsCommand;
import finance.commands.BankAccount.UpdateAccountCommand;
import finance.commands.Category.CreateCategoryCommand;
import finance.commands.Category.DeleteCategoryCommand;
import finance.commands.Category.GetCategoriesCommand;
import finance.commands.Category.UpdateCategoryCommand;
import finance.commands.Operation.CreateOperationCommand;
import finance.commands.Operation.DeleteOperationCommand;
import finance.commands.Operation.GetOperationsCommand;
import finance.commands.Operation.UpdateOperationCommand;
import finance.facades.FinanceFacade;
import finance.interfaces.ICommand;

import java.time.LocalDate;

import static finance.enums.OperationType.EXPENSES;
import static finance.enums.OperationType.INCOME;

public class CommandParser {
    private FinanceFacade financeFacade;

    public CommandParser(FinanceFacade financeFacade) {
        this.financeFacade = financeFacade;
    }

    public ICommand parse(String input) {
        String[] parts = input.split(" ");

        if (parts.length < 2) {
            throw new IllegalArgumentException("Invalid command");
        }

        String action = parts[0];
        String entity = parts[1];

        int id;
        int type;
        double amount;
        LocalDate date;
        int bankAccountId;
        int categoryId;
        StringBuilder description = new StringBuilder();
        String name;
        double balance;

        switch (action.toLowerCase() + " " + entity.toLowerCase()) {
            case "create account":
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Expected 2 arguments, got " + (parts.length - 2));
                }

                name = parts[2];
                balance = Double.parseDouble(parts[3]);
                return new CreateAccountCommand(financeFacade, name, balance);

            case "update account":
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Expected 3 arguments, got " + (parts.length - 2));
                }

                id = Integer.parseInt(parts[2]);
                name = parts[3];
                balance = Double.parseDouble(parts[4]);
                return new UpdateAccountCommand(financeFacade, id, name, balance);

            case "delete account":
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Expected 1 arguments, got " + (parts.length - 2));
                }

                id = Integer.parseInt(parts[2]);
                return new DeleteAccountCommand(financeFacade, id);

            case "create category":
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Expected 2 arguments, got " + (parts.length - 2));
                }

                type = switch (parts[2].toLowerCase()) {
                    case "i", "income" -> INCOME.ordinal();
                    case "e", "expenses" -> EXPENSES.ordinal();
                    default -> throw new IllegalArgumentException("Invalid category type.");
                };
                name = parts[3];

                return new CreateCategoryCommand(financeFacade, type, name);

            case "update category":
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Expected 2 arguments, got " + (parts.length - 2));
                }

                id = Integer.parseInt(parts[2]);
                name = parts[3];

                return new UpdateCategoryCommand(financeFacade, id, name);

            case "delete category":
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Expected 3 arguments, got " + (parts.length - 2));
                }

                id = Integer.parseInt(parts[2]);

                return new DeleteCategoryCommand(financeFacade, id);

            case "create operation":
                if (parts.length < 7) {
                    throw new IllegalArgumentException("Expected 5 or 6 arguments, got " + (parts.length - 2));
                }

                type = switch (parts[2].toLowerCase()) {
                    case "i", "income" -> INCOME.ordinal();
                    case "e", "expenses" -> EXPENSES.ordinal();
                    default -> throw new IllegalArgumentException("Invalid category type.");
                };
                amount = Double.parseDouble(parts[3]);
                date = parts[4].equalsIgnoreCase("now") ? LocalDate.now() : LocalDate.parse(parts[4]);
                bankAccountId = Integer.parseInt(parts[5]);
                categoryId = Integer.parseInt(parts[6]);

                if (parts.length >= 8) {
                    for (int i = 7; i < parts.length; i++) {
                        description.append(parts[i]).append(" ");
                    }

                    return new CreateOperationCommand(financeFacade, type, amount, date,
                            bankAccountId, categoryId, description.toString());
                }

                return new CreateOperationCommand(financeFacade, type, amount, date,
                        bankAccountId, categoryId, "");

            case "update operation":
                if (parts.length < 8) {
                    throw new IllegalArgumentException("Expected 6 or 7 arguments, got " + (parts.length - 2));
                }

                id = Integer.parseInt(parts[2]);
                type = switch (parts[3].toLowerCase()) {
                    case "i", "income" -> INCOME.ordinal();
                    case "e", "expenses" -> EXPENSES.ordinal();
                    default -> throw new IllegalArgumentException("Invalid category type.");
                };
                amount = Double.parseDouble(parts[4]);
                date = parts[5].equalsIgnoreCase("now") ? LocalDate.now() : LocalDate.parse(parts[4]);
                bankAccountId = Integer.parseInt(parts[6]);
                categoryId = Integer.parseInt(parts[7]);

                if (parts.length >= 9) {
                    for (int i = 8; i < parts.length; i++) {
                        description.append(parts[i]).append(" ");
                    }

                    return new UpdateOperationCommand(financeFacade, id, type, amount, date,
                            bankAccountId, categoryId, description.toString());
                }

                return new UpdateOperationCommand(financeFacade, id, type, amount, date,
                        bankAccountId, categoryId, "");

            case "delete operation":
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Expected 3 arguments, got " + (parts.length - 2));
                }

                id = Integer.parseInt(parts[2]);

                return new DeleteOperationCommand(financeFacade, id);

            case "get accounts":
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Expected 0 arguments, got " + (parts.length - 2));
                }

                return new GetAccountsCommand(financeFacade);

            case "get categories":
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Expected 0 arguments, got " + (parts.length - 2));
                }

                return new GetCategoriesCommand(financeFacade);

            case "get operations":
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Expected 0 arguments, got " + (parts.length - 2));
                }

                return new GetOperationsCommand(financeFacade);

            default:
                throw new IllegalArgumentException("Unknown command: " + input);
        }
    }
}
