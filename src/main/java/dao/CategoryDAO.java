package dao;

import model.Category;

import java.util.List;

public interface CategoryDAO {

    public void add(Category category, int parentId);

    public void addChild(int parentId, int childID);

    public void remove(int id);

    public boolean find(int id);

    public Category get(int id);

    public List<Category> getParentCategories();

    public List<Category> getChildCategories(int parentId);

    public void update(Category category, int id);

}