package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File("src/main/java/resources/DBInfo.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        String URL = properties.getProperty("URL");
        String USER = properties.getProperty("USER");
        String PASSWORD = properties.getProperty("PASSWORD");

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private ConnectionFactory(){}



}