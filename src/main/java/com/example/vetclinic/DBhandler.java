package com.example.vetclinic;

import java.sql.*;

public class DBhandler {
    private static DBhandler instance;
    private Connection dbConnector;

    private DBhandler() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.dbConnector = DriverManager.getConnection("jdbc:mysql://" + Configs.dbHost + ":" +
                Configs.dbPort + "/" + Configs.dbName,
                Configs.dbUser, Configs.dbPass);
    }

    public Connection getConnection() {
        return dbConnector;
    }

    public static DBhandler getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new DBhandler();
        } else if (instance.getConnection().isClosed()) {
            instance = new DBhandler();
        }

        return instance;
    }

    public User createUser(User user) {
        String password = Controller.hashPass(user.getPassword());
        String insertUser = "INSERT INTO " + Constants.USER_TABLE + "(" +
                Constants.USER_LOGIN + "," +
                Constants.USER_PASSWORD + "," +
                Constants.USER_ROLE_ID + ")" +
                "VALUES(?,?,3);";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(insertUser, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);

            preparedStatement.execute();

            ResultSet generatedId = preparedStatement.getGeneratedKeys();
            generatedId.next();

            int id = generatedId.getInt(1);
            user.setId(id);

            System.out.println(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Owner createOwner(Owner owner) {
        String insertOwner = "INSERT INTO " + Constants.OWNER_TABLE + "(" +
                Constants.OWNER_USER_ID + "," +
                Constants.OWNER_NAME + "," +
                Constants.OWNER_PHONE + "," +
                Constants.OWNER_ADDRESS + ")" +
                "VALUES(?,?,?,?);";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(insertOwner, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, owner.getUserId());
            preparedStatement.setString(2, owner.getName());
            preparedStatement.setString(3, owner.getNumber());
            preparedStatement.setString(4, owner.getAddress());

            preparedStatement.execute();

            ResultSet generatedId = preparedStatement.getGeneratedKeys();
            generatedId.next();

            int id = generatedId.getInt(1);
            owner.setId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owner;
    }

    public User getUser(String login, String password) {
        User user = null;
        password = Controller.hashPass(password);
        String select = "SELECT * FROM " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_LOGIN + "=? AND " + Constants.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Constants.USER_ID);
                int roleId = resultSet.getInt(Constants.USER_ROLE_ID);
                user = new User(id, login, password, roleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Owner getOwner(int userId) {
        Owner owner = null;
        String select = "SELECT * FROM " + Constants.USER_TABLE + " AS u " +
                "INNER JOIN " + Constants.OWNER_TABLE + " AS o ON u.id = o.user_id " +
                "WHERE u.id = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int ownerId = resultSet.getInt(Constants.OWNER_ID);
                String name = resultSet.getString(Constants.OWNER_NAME);
                String phoneNumber = resultSet.getString(Constants.OWNER_PHONE);
                String address = resultSet.getString(Constants.OWNER_ADDRESS);

                owner = new Owner(ownerId, name, phoneNumber, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owner;
    }
}
