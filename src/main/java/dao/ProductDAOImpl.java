package dao;

import model.Product;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO{


    @Override
    public void add(Product product) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("INSERT INTO PRODUCTS (NAME,PRICE,MARK,DESCRIPTION,CATEGORY) " +
                    "VALUES ('%S',%S,%S,'%S',%S);", product.getName(), product.getPrice(), product.getMark(),
                    product.getDescription(), product.getCategory().getId());

            statement.execute(SQLRequest);
            ResultSet resultSet = statement.executeQuery("SELECT ID FROM ADDRESS ORDER BY ID DESC LIMIT 1;");

            if(resultSet.next()){
                product.setId(resultSet.getInt("ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("DELETE FROM PRODUCTS WHERE ID=%S;", id);
            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product get(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM PRODUCTS WHERE ID=%S;", id);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            return createProduct(resultSet, statement);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean find(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT ID FROM PRODUCTS WHERE ID=%S;", id);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if(resultSet.next()){
                return true;
            } else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void update(Product product, int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("UPDATE PRODUCTS SET NAME='%S', PRICE=%S, MARK=%S, " +
                    "DESCRIPTION='%S' WHERE ID=%S;", product.getName(), product.getPrice(),
                    product.getMark(), product.getDescription(), id);

            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAllProducts(){
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM PRODUCTS");
            ResultSet resultSet = statement.executeQuery(SQLRequest);
            LinkedList<Product> products = new LinkedList<>();

            while (resultSet.next()){
                products.add(createProduct(resultSet, statement));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Product> getProductsByCategory(int categoryId) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM PRODUCTS WHERE CATEGORY=%S", categoryId);
            ResultSet resultSet = statement.executeQuery(SQLRequest);
            LinkedList<Product> products = new LinkedList<>();

            while (resultSet.next()){
                products.add(createProduct(resultSet, statement));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Product createProduct(ResultSet resultSet, Statement statement) {
        try {
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setDescription(resultSet.getString("DESCRIPTION"));
                product.setMark(resultSet.getShort("MARK"));
                product.setPrice(resultSet.getLong("PRICE"));
                int categoryId = resultSet.getInt("CATEGORY");

                CategoryDAO dao = new CategoryDAOImpl();
                product.setCategory(dao.get(categoryId));

                return product;
            } else return null;

        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
}