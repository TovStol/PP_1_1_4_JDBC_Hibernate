package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {


    private static Util INSTANCE;
    private SessionFactory sessionFactory = null;

    private Util() {

    }

    public static Util getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Util();
        }
        return INSTANCE;
    }

    public SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties setting = new Properties();
                setting.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                setting.put(Environment.URL, "jdbc:mysql://localhost:3306/store?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
                setting.put(Environment.USER, "root");
                setting.put(Environment.PASS, "xsl8gy7r");

                setting.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                setting.put(Environment.SHOW_SQL, "true");

                setting.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                setting.put(Environment.HBM2DDL_AUTO, "");

                configuration.addAnnotatedClass(User.class);
                configuration.setProperties(setting);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/store", "root", "xsl8gy7r");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }


}
