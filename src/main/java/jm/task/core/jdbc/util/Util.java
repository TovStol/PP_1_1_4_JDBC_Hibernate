package jm.task.core.jdbc.util;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/store", "root", "xsl8gy7r");
        } catch (SQLException e) {
            System.out.println("Conection failed" + e);
        }
        return connection;
    }


}
