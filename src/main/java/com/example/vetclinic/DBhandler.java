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

    public void createOwner(Owner owner) {
        String insert = "INSERT INTO " + Constants.USER_TABLE + "(" +
                Constants.USER_NAME + "," +
                Constants.USER_LOGIN + "," +
                Constants.USER_PASSWORD + ")" +
                "VALUES(?,?,?);" +
                "INSERT INTO " + Constants.OWNER_TABLE + "(" +
                Constants.OWNER_NAME + "," +
                Constants.OWNER_PHONE + "," +
                Constants.OWNER_ADDRESS + ")" +
                "VALUES(?,?,?)";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(insert);
            preparedStatement.setString(1, owner.getName());
            preparedStatement.setString(2, owner.getLogin());
            preparedStatement.setString(3, owner.getPassword());
            preparedStatement.setString(4, owner.getName());
            preparedStatement.setString(5, owner.getAddress());
            preparedStatement.setString(6, owner.getNumber());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(Owner owner) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_LOGIN + "=? AND " + Constants.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setString(1, owner.getLogin());
            preparedStatement.setString(2, owner.getPassword());

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }
}
