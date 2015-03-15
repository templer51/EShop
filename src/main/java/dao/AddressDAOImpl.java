package dao;

import model.Address;
import utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddressDAOImpl implements AddressDAO{


    @Override
    public void add(Address address) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("INSERT INTO ADDRESS " +
                "(COUNTRY, CITY, STREET, BUILDING, ROOM) VALUES ('%s','%s','%s','%s','%s');"
                , address.getCountry(), address.getCity(), address.getStreet()
                , address.getBuilding(), address.getRoom());

            statement.execute(SQLRequest);
            ResultSet resultSet = statement.executeQuery("SELECT ID FROM ADDRESS ORDER BY ID DESC LIMIT 1;");

            if(resultSet.next()){
                address.setId(resultSet.getInt("ID"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("DELETE FROM ADDRESS WHERE ID=" + id +";");
            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address get(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT * FROM ADDRESS WHERE ID=" + id +";");
            ResultSet resultSet = statement.executeQuery(SQLRequest);
            Address address = new Address();

            if(resultSet.next()){
                address.setId(resultSet.getInt("ID"));
                address.setCountry(resultSet.getString("Country"));
                address.setCity(resultSet.getString("City"));
                address.setBuilding(resultSet.getString("Building"));
                address.setStreet(resultSet.getString("Street"));
                address.setRoom(resultSet.getString("Room"));

                return address;
            } else return  null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Address get(String name) {
        return null;
    }

    @Override
    public boolean find(int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("SELECT ID FROM ADDRESS WHERE ID=" + id +";");
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
    public boolean find(String name) {
        return false;
    }

    @Override
    public void update(Address address, int id) {
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            String SQLRequest = String.format("UPDATE ADDRESS SET COUNTRY='%S', CITY='%S'," +
                    " STREET='%S', BUILDING='%S', ROOM='%S' WHERE ID=" + id + ";",
                    address.getCountry(), address.getCity(), address.getStreet(),
                    address.getBuilding(), address.getRoom());

            statement.execute(SQLRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}