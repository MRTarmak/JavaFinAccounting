package finance.facades;

import finance.domains.BankAccount;
import finance.domains.Category;
import finance.domains.Operation;
import finance.services.BankAccountService;
import finance.services.CategoryService;
import finance.services.OperationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class FinanceFacade {
    BankAccountService bankAccountService = new BankAccountService();

    CategoryService categoryService = new CategoryService();

    OperationService operationService = new OperationService();

    public void createBankAccount(String name, double balance) {
        bankAccountService.createBankAccount(name, balance);
    }

    public void updateBankAccount(int id, String name, double balance) {
        bankAccountService.updateBankAccount(id, name, balance);
    }

    public void deleteBankAccount(int id) {
        bankAccountService.deleteBankAccount(id);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    public void createCategory(int type, String name) {
        categoryService.createCategory(type, name);
    }

    public void updateCategory(int id, String name) {
        categoryService.updateCategory(id, name);
    }

    public void deleteCategory(int id) {
        categoryService.deleteCategory(id);
    }

    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    public void createOperation(int type, double amount, LocalDate date,
                                int bankAccountId, int categoryId, String description) {
        if (Objects.isNull(bankAccountService.getBankAccount(bankAccountId))) {
            throw new IllegalArgumentException("There is no account with such id.");
        }

        if (Objects.isNull(categoryService.getCategory(categoryId))) {
            throw new IllegalArgumentException("There is no category with such id.");
        }

        if (type != categoryService.getCategory(categoryId).getType()) {
            throw new IllegalArgumentException("Operation type do not match the category type.");
        }

        operationService.createOperation(type, amount, date, bankAccountId, categoryId, description);
    }

    public void updateOperation(int id, int type, double amount, LocalDate date,
                                int bankAccountId, int categoryId, String description) {
        if (Objects.isNull(bankAccountService.getBankAccount(bankAccountId))) {
            throw new IllegalArgumentException("There is no account with such id.");
        }

        if (Objects.isNull(categoryService.getCategory(categoryId))) {
            throw new IllegalArgumentException("There is no category with such id.");
        }

        if (type != categoryService.getCategory(categoryId).getType()) {
            throw new IllegalArgumentException("Operation type do not match the category type.");
        }

        operationService.updateOperation(id, type, amount, date, bankAccountId, categoryId, description);
    }

    public void deleteOperation(int id) {
        operationService.deleteOperation(id);
    }

    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }
}
