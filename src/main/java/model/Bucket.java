package model;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bucket {
    int id;
    private User user;
    private Map<Product, Integer> products;
    private Date deliveryDate;

    public Bucket(User user) {
        this.user = user;
        products = new LinkedHashMap<Product, Integer>();
    }

    public void addProduct(Product product, int count){
        products.put(product, count);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getPrice(){
        long price = 0;

        for(Map.Entry<Product, Integer> entry : products.entrySet()){
            Product product = entry.getKey();
            int count = entry.getValue();
            price += product.getPrice() * count;
        }

        if(user.getUserType() == UserType.Editor){
            price = (long) (price * 0.95);
        } else if(user.getUserType() == UserType.Admin){
            price = (long) (price * 0.9);
        }

        return price;
    }

    public Product[] getProducts(){
        Product[] productsArray = new Product[products.size()];
        int i = 0;

        for(Map.Entry<Product, Integer> entry : products.entrySet()){
            productsArray[i] = entry.getKey();
            i++;
        }

        return productsArray;
    }
}