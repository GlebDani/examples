package ru.danilenko.util;


import java.io.FileInputStream;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * class for db connection
 */
public class ConnectionToDB {

    private String url;
    private String username;
    private String password;

//    private String driver;


    public ConnectionToDB(String url, String username,String password){
        this.url = url;
        this.username = username;
        this.password = password;
    }
    public ConnectionToDB(){
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/database.properties"));
            url = properties.getProperty("url");
            username = properties.getProperty("username_db");
            password = properties.getProperty("password");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * static method to get connection to db
     * @return Connection
     */
    public Connection getConnection() {

        Connection connection = null;

        try{
                Class.forName("org.postgresql.Driver");
                connection  = DriverManager.getConnection(url, username,password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }


}
