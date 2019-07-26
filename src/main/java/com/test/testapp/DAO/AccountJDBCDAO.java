package com.test.testapp.DAO;

import com.test.testapp.entity.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountJDBCDAO implements AccountDAO {
    public boolean add(Account account) {
        Connection connection = getConnection();

        boolean isWrite = false;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts(name, surname, email, password) VALUES (?, ?, ?, ?)");

            statement.setString(1, account.getName());
            statement.setString(2, account.getSurname());
            statement.setString(3, account.getEmail());
            statement.setString(4, account.getPassword());

            isWrite = statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isWrite;
    }

    public List<Account> getAll() {
        List<Account> allAccounts = new ArrayList<Account>();
        Connection connection = getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts");

            ResultSet resultSet =statement.executeQuery();
            while (resultSet.next()){
                Account account = new Account();

                account.setName(resultSet.getString(1));
                account.setSurname(resultSet.getString(2));
                account.setEmail(resultSet.getString(3));
                account.setPassword(resultSet.getString(4));

                allAccounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allAccounts;
    }

    public Account getByName(String name) {
        Connection connection = getConnection();
        Account account = null;

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM accounts WHERE name = ?");

            statement.setString(1, name);

            ResultSet resultSet =statement.executeQuery();

            while (resultSet.next()){
                account = new Account();

                account.setName(resultSet.getString(1));
                account.setSurname(resultSet.getString(2));
                account.setEmail(resultSet.getString(3));
                account.setPassword(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }

    public int updateSurname(String name, String surname) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try{
            statement = connection.prepareStatement("UPDATE accounts SET surname = ? WHERE name = ?");
            statement.setString(1, surname);
            statement.setString(2, name);

            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {

                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    public int remove(String name) {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            statement = connection.prepareStatement("DELETE FROM accounts WHERE name = ? ");

            statement.setString(1, name);

            count = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && statement != null) {
                try {
                    connection.close();
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return count;
    }

    private Connection getConnection(){
        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "testuser", "test");
            return connection;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
