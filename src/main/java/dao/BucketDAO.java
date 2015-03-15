package dao;

import model.Product;

public interface BucketDAO {

    public void add(Product product);

    public void delete(int productId);

    public void update();



}