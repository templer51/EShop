package manager;

import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.ProductDAO;
import dao.ProductDAOImpl;
import model.Product;

import java.util.List;

public class ProductManagerImpl implements ProductManager{

    ProductDAO productDAO = new ProductDAOImpl();
    CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public void create(String name, String description, long price, int categoryId) {
        Product product = new Product(name, description, price, categoryDAO.get(categoryId));
        productDAO.add(product);
    }

    @Override
    public void change(int id, String name, String description, long price, int categoryId) {
        Product product = new Product(name, description, price, categoryDAO.get(categoryId));
        productDAO.update(product, id);
    }

    @Override
    public void delete(int id) {
        productDAO.remove(id);
    }

    @Override
    public boolean find(int id) {
        return productDAO.find(id);
    }

    @Override
    public Product get(int id) {
        return productDAO.get(id);
    }

    @Override
    public List<Product> getProducts(int typeId) {
        return productDAO.getProductsByCategory(typeId);
    }

    @Override
    public List<Product> getProducts() {
        return productDAO.getAllProducts();
    }
}