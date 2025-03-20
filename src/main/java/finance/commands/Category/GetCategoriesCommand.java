package finance.commands.Category;

import finance.facades.FinanceFacade;
import finance.interfaces.ICommand;

public class GetCategoriesCommand implements ICommand {
    private FinanceFacade financeFacade;

    public GetCategoriesCommand(FinanceFacade financeFacade) {
        this.financeFacade = financeFacade;
    }

    @Override
    public void execute() {
        financeFacade.getAllCategories().forEach(System.out::println);
    }
}
