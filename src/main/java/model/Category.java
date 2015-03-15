package model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private int id;
    private String name;
    private Category parent;
    private List<Category> childs;

    public Category(){}

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
        childs = new ArrayList<Category>(5);
    }

    //@return index or -1
    public int find(Category category){
        int index = childs.indexOf(category);
        return index;
    }

    public boolean isRootCategory(){
        if(parent == null) return true;
        else return false;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category category){
        parent = category;
    }

    public void addChild(Category category){
        childs.add(category);
    }

    public void deleteChild(Category category){
        childs.remove(category);
    }

    public void deleteChild(int index){
        childs.remove(index);
    }
}
