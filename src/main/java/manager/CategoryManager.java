package manager;

import model.Category;

import java.util.List;

public interface CategoryManager {

    public void add(String name, int parentId);

    public void remove(int id);

    public void change(String name, int id);

    public void setChild(int parentId, int childId);

    public boolean find(int id);

    public Category get(int id);

    public List<Category> getParentCategories();

    public List<Category> getChildCategories(int parentId);
}
