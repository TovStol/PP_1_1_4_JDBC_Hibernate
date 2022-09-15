package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String url = "jdbc:mysql://localhost/store";
    private static final String username = "root";
    private static final String password = "xsl8gy7r";


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Conection failed" + e);
        }
        return connection;
    }


}
