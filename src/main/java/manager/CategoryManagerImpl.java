package manager;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import model.Category;

import java.util.List;

public class CategoryManagerImpl implements CategoryManager{

    private CategoryDAO dao = new CategoryDAOImpl();

    @Override
    public void add(String name, int parentId) {
        Category category = new Category(name);
        dao.add(category, parentId);
    }

    @Override
    public void remove(int id) {
        dao.remove(id);
    }

    @Override
    public void change(String name, int id) {
        Category category = new Category(name);
        dao.update(category, id);
    }

    @Override
    public void setChild(int parentId, int childId) {
        dao.addChild(parentId, childId);
    }

    @Override
    public boolean find(int id) {
        return dao.find(id);
    }

    @Override
    public Category get(int id) {
        return dao.get(id);
    }

    @Override
    public List<Category> getParentCategories() {
        return dao.getParentCategories();
    }

    @Override
    public List<Category> getChildCategories(int parentId) {
        return dao.getChildCategories(parentId);
    }
}