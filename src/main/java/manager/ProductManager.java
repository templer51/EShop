package manager;

import model.Product;

import java.util.List;

public interface ProductManager {

    public void create(String name, String description, long price, int categoryId);

    public void change(int id, String name, String description, long price, int categoryId);

    public void delete(int id);

    public boolean find(int id);

    public Product get(int id);

    public List<Product> getProducts(int typeId);

    public List<Product> getProducts();

}