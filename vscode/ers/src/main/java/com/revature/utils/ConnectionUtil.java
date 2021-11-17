package com.revature.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    private static Connection con;

    public static Connection getConnectionFromFile() throws IOException, SQLException {
        if(con == null || con.isClosed()) {
            Properties prop = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            prop.load(loader.getResourceAsStream("prop.properties"));
            String url = prop.getProperty("url");
            String username = prop.getProperty("username");
            String password = prop.getProperty("password");
            con = DriverManager.getConnection(url, username, password);
        }
        return con;
    }
}