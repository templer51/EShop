package dao;

import model.Address;
import model.User;
import model.UserType;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UserDAOImpl implements UserDAO{
    @Override
    public void add(User user) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){
            AddressDAOImpl dao = new AddressDAOImpl();
            dao.add(user.getAddress());

            String SQLRequest = String.format("SELECT ID FROM USER_TYPE WHERE NAME='%S';", user.getUserType());
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            int userType = 0;

            if(resultSet.next()){
                userType = resultSet.getInt("ID");
            } else return;

            SQLRequest = String.format("INSERT INTO USERS VALUES " +
                    "('%S','%S','%S','%S','%S',%S,%S);", user.getName(), user.getLogin(),
                    user.getPassword(), user.getEmail(), user.getPhone(), userType,
                    user.getAddress().getId());

            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(String login) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("DELETE FROM USERS WHERE LOGIN=%S;", login);
            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean find(String login) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT LOGIN FROM USERS WHERE LOGIN=%S;", login);
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
    public User get(String login) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM USERS WHERE LOGIN=%S;", login);
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            if(resultSet.next()){
                return createUser(resultSet, statement);
            } else return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("UPDATE USERS SET NAME='%S', PASSWORD='%S, " +
                    "EMAIL='%S, PHONE='%S WHERE LOGIN='%S;", user.getName(), user.getPassword(),
                    user.getEmail(), user.getPhone(), user.getLogin());
            statement.execute(SQLRequest);

            SQLRequest = String.format("SELECT ADDRESS FROM USERS WHERE LOGIN='%S';", user.getLogin());
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            int addressId = -1;

            if(resultSet.next()){
               addressId = resultSet.getInt("ID");
            }

            AddressDAO dao = new AddressDAOImpl();
            dao.update(user.getAddress(), addressId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            String SQLRequest = String.format("SELECT * FROM USERS;");
            ResultSet resultSet = statement.executeQuery(SQLRequest);

            LinkedList<User> users = new LinkedList<User>();

            while (resultSet.next()) {
                User user = createUser(resultSet, statement);
                users.add(user);
            }

            return users;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private User createUser(ResultSet resultSet, Statement statement){
        try {
            User user = new User();
            user.setLogin(resultSet.getString("LOGIN"));
            user.setPassword(resultSet.getString("PASSWORD"));
            user.setName(resultSet.getString("NAME"));
            user.setEmail(resultSet.getString("EMAIL"));
            user.setPhone(resultSet.getString("PHONE"));
            int userType = resultSet.getInt("USER_TYPE");
            int addressId = resultSet.getInt("ADDRESS");

            AddressDAO dao = new AddressDAOImpl();
            Address address = dao.get(addressId);
            user.setAddress(address);

            String SQLRequest = String.format("SELECT NAME FROM USER_TYPE WHERE ID=%S;", userType);
            ResultSet result = statement.executeQuery(SQLRequest);

            if (result.next()) {
                String name = result.getString("Name");
                if (name.equals("CUSTOMER")) {
                    user.setUserType(UserType.Customer);
                } else if (name.equals("EDITOR")) {
                    user.setUserType(UserType.Editor);
                } else if (name.equals("ADMIN")) {
                    user.setUserType(UserType.Admin);
                } else {
                    throw new SQLException();
                }
            }

            return user;

        } catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
}