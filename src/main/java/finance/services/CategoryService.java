package finance.services;

import finance.domains.Category;
import finance.factories.CategoryFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryService {
    private HashMap<Integer, Category> categories = new HashMap<>();

    private CategoryFactory factory = new CategoryFactory();

    public void createCategory(int type, String name) {
        Category category = factory.createCategory(categories.size(), type, name);

        categories.put(category.getId(), category);
    }

    public void updateCategory(int id, String name) {
        if (!categories.containsKey(id)) {
            throw new IllegalArgumentException("There is no categories with such id.");
        }

        Category newCategory = factory.createCategory(id, categories.get(id).getType(), name);

        categories.put(id, newCategory);
    }

    public void deleteCategory(int id) {
        if (!categories.containsKey(id)) {
            throw new IllegalArgumentException("There is no categories with such id.");
        }

        categories.remove(id);
    }

    public Category getCategory(int id) {
        return categories.get(id);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories.values());
    }
}
