package com.digi.repository.impl;

import com.digi.enums.Status;
import com.digi.model.User;
import com.digi.repository.UserRepository;
import com.digi.util.MyDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJDBC implements UserRepository {
    @Override
    public void saveUser(User user) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users values (?,?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setInt(4, user.getYear());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setString(7, user.getStatus().toString());
            preparedStatement.setString(8, user.getVerificationCode());
            preparedStatement.setString(9, user.getResetToken());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void verification(int id) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set status = ?, verification_code = ? where id = ?");

            preparedStatement.setString(1, Status.ACTIVE.toString());
            preparedStatement.setString(2, null);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changePassword(int id, String password) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set password = ?, reset_token = ? where id = ?");

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, null);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setResetToken(int id, String token) {
        Connection connection = MyDataSource.getConnection();

        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set reset_token = ? where id = ?");

            preparedStatement.setString(1, token);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserById(int id) {
        Connection connection = MyDataSource.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where id = ?");

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> usersFromDB = getUsersFromDB(resultSet);
            if (!usersFromDB.isEmpty()) {
                user = usersFromDB.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        Connection connection = MyDataSource.getConnection();
        User user = null;
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from users where email = ?");

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> usersFromDB = getUsersFromDB(resultSet);
            if (!usersFromDB.isEmpty()) {
                user = usersFromDB.get(0);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    private List<User> getUsersFromDB(ResultSet resultSet) {
        List<User> users = new ArrayList<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int year = resultSet.getInt("year");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String status = resultSet.getString("status");
                String verificationCode = resultSet.getString("verification_code");
                String resetToken = resultSet.getString("reset_token");
                users.add(new User(id, firstName, lastName, year, email, password, Enum.valueOf(Status.class, status), verificationCode, resetToken));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }




}
