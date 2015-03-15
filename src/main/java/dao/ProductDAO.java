package dao;

import model.Product;

import java.util.List;

public interface ProductDAO {

    public void add(Product product);

    public void remove(int id);

    public Product get(int id);

    public boolean find(int id);

    public void update(Product product, int id);

    public List<Product> getAllProducts();

    public List<Product> getProductsByCategory(int id);
}