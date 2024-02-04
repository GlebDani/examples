package ru.danilenko.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionToDB {

    private ConnectionToDB(){

    }

    public static  Connection getConnection() {


//        final String URL = "jdbc:postgresql://localhost:5432/Y-lab_bd";
//        final String USERNAME = "y_lab";
//        final String PASSWORD = "ylab";

        Connection connection = null;

        try{
                Properties properties = new Properties();
                properties.load(new FileInputStream("src/main/resources/database.properties"));
                Class.forName(properties.getProperty("driver"));
                connection  = DriverManager.getConnection(properties.getProperty("url"),
                                                          properties.getProperty("username_db"),
                                                          properties.getProperty("password"));

        } catch (ClassNotFoundException | IOException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
