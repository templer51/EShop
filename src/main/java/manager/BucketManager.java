package manager;

import model.Product;
import model.User;

public interface BucketManager {

    public void create(User user, Product product, int count);

    public void add(Product product, int count);

    public void clear();

    public void delete(int productId);

    public long calculatePrice();

    public void setDeliveryDate();

}
