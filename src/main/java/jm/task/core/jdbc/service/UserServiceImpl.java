package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserServiceImpl implements UserService {
    private Util util = new Util();

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS users " + "(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL , " + "lastName VARCHAR(20) NOT NULL , age SMALLINT NOT NULL)";
        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {

            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Таблица не создана");
        }
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS users";

        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Таблица не удалена");
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlCommand = "INSERT INTO store.users (name, lastName, age ) VALUES (?, ?, ?)";

        try (Connection connection = util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.println("User не добавлен");

        }
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM store.users WHERE id=?";
        try (Connection connection = util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("User не удален");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sqlCommand = "SELECT * FROM store.users";
        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка получения всех User");
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sqlCommand = "DELETE FROM store.users";
        try (Connection connection = util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            System.out.println("User не удален");
        }
    }
}
