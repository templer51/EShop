package dao;

import model.Category;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO{


    @Override
    public void add(Category category, int parentId) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("INSERT INTO CATEGORIES (NAME)" +
                    " VALUES ('%S');", category.getName());
            statement.execute(SQLRequest);

            if(parentId >= 0){
                addChild(parentId, category.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addChild(int parentId, int childID) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("INSERT INTO category_hierarchy (ROOT_ID,CHILD_ID)" +
                    " VALUES (" + parentId + "," + childID + ");");
            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("DELETE FROM CATEGORIES WHERE ID=" + id + ";");
            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean find(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT ID FROM CATEGORIES WHERE ID=" + id + ";");
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if(resultSet.next()){
                return true;
            }else return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Category get(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM CATEGORIES WHERE ID=" + id + ";");
            ResultSet resultSet = statement.executeQuery(SQLRequest);
            Category category = new Category();

            if(resultSet.next()){
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("NAME"));

                SQLRequest = String.format("SELECT * FROM categories WHERE ID IN " +
                        "(SELECT CHILD_ID FROM category_hierarchy WHERE ROOT_ID=" + id + ");");
                resultSet = statement.executeQuery(SQLRequest);

                if(resultSet.next()){
                    Category childCategory = new Category();
                    childCategory.setId(resultSet.getInt("ID"));
                    childCategory.setName(resultSet.getString("NAME"));
                    category.addChild(childCategory);
                }

                return category;

            } else return null;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Category> getParentCategories() {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM categories WHERE ID IN" +
                    "(SELECT ROOT_ID FROM category_hierarchy);");

            ResultSet resultSet = statement.executeQuery(SQLRequest);
            ArrayList<Category> categories = new ArrayList<>();

            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("Name"));
                categories.add(category);
            }

            categories.trimToSize();
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Category> getChildCategories(int parentId) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM categories WHERE ID IN" +
                    "(SELECT CHILD_ID FROM category_hierarchy);");

            ResultSet resultSet = statement.executeQuery(SQLRequest);
            ArrayList<Category> categories = new ArrayList<>();

            while (resultSet.next()){
                Category category = new Category();
                category.setId(resultSet.getInt("ID"));
                category.setName(resultSet.getString("Name"));
                categories.add(category);
            }

            categories.trimToSize();
            return categories;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Category category, int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("UPDATE CATEGORIES SET NAME='%S' WHERE ID="
                    + id + ";", category.getName());

            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}