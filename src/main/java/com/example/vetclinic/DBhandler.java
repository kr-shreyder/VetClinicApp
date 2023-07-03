package com.example.vetclinic;

import java.sql.*;
import java.util.ArrayList;

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

    public User getUserByOwnerId(int ownerId) {
        User user = null;
        String select = "SELECT * FROM " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_ID + " = (SELECT " + Constants.OWNER_USER_ID +
                " FROM " + Constants.OWNER_TABLE +
                " WHERE " + Constants.OWNER_ID + " = ?)";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, ownerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Constants.USER_ID);
                String login = resultSet.getString(Constants.USER_LOGIN);
                String password = resultSet.getString(Constants.USER_PASSWORD);
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
        String select = "SELECT * FROM " + Constants.OWNER_TABLE + " WHERE user_id = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Constants.OWNER_ID);
                String name = resultSet.getString(Constants.OWNER_NAME);
                String phoneNumber = resultSet.getString(Constants.OWNER_PHONE);
                String address = resultSet.getString(Constants.OWNER_ADDRESS);

                owner = new Owner(id, userId, name, phoneNumber, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return owner;
    }

    public Doctor getDoctor(int userId) {
        Doctor doctor = null;
        String select = "SELECT * FROM " + Constants.DOCTOR_TABLE + " WHERE " + Constants.DOCTOR_USER_ID + " = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt(Constants.DOCTOR_ID);
                String name = resultSet.getString(Constants.DOCTOR_NAME);
                String phoneNumber = resultSet.getString(Constants.DOCTOR_PHONE);
                String address = resultSet.getString(Constants.DOCTOR_ADDRESS);

                doctor = new Doctor(id, userId, name, phoneNumber, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctor;
    }

    public Pet getPet(int ownerId) {
        Pet pet = null;
        String select = "SELECT p.id, p.name, p.owner_id, b.name AS breed_name " +
                "FROM " + Constants.PET_TABLE + " AS p " +
                "INNER JOIN " + Constants.BREED_TABLE + " AS b ON p.breed_id = b.id " +
                "WHERE p.owner_id = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, ownerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int petId = resultSet.getInt(Constants.PET_ID);
                String petName = resultSet.getString(Constants.PET_NAME);
                int ownId = resultSet.getInt(Constants.PET_OWNER);
                String breedName = resultSet.getString(Constants.BREED_NAME);

                pet = new Pet(petId, petName, ownId, breedName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pet;
    }

    public ArrayList<Pet> getPetsByOwnerId(int ownerId) {
        ArrayList<Pet> pets = new ArrayList<>();

        String select = "SELECT p.id, p.name, p.owner_id, b.name AS breed_name " +
                "FROM " + Constants.PET_TABLE + " AS p " +
                "INNER JOIN " + Constants.BREED_TABLE + " AS b ON p.breed_id = b.id " +
                "WHERE p.owner_id = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, ownerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int petId = resultSet.getInt(Constants.PET_ID);
                String petName = resultSet.getString(Constants.PET_NAME);
                int ownId = resultSet.getInt(Constants.PET_OWNER);
                String breedName = resultSet.getString(Constants.BREED_NAME);

                Pet pet = new Pet(petId, petName, ownId, breedName);
                pets.add(pet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    public void updatePet(Pet pet) {
        String selectBreed = "SELECT id FROM " + Constants.BREED_TABLE +
                " WHERE name = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(selectBreed);
            preparedStatement.setString(1, pet.getBreed());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int brId = resultSet.getInt(Constants.BREED_ID);

                String updatePet = "UPDATE " + Constants.PET_TABLE +
                        " SET " + Constants.PET_NAME + "= ?, " + Constants.PET_OWNER +"= ?, " + Constants.PET_BREED +" = ?" +
                        " WHERE " + Constants.PET_ID + " = ?";

                try {
                    PreparedStatement preparedStatementPet = dbConnector.prepareStatement(updatePet);
                    preparedStatementPet.setString(1, pet.getName());
                    preparedStatementPet.setInt(2, pet.getOwnerId());
                    preparedStatementPet.setInt(3, brId);
                    preparedStatementPet.setInt(4, pet.getPetId());

                    preparedStatementPet.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pet createPet(Pet pet) {
        String selectBreed = "SELECT id FROM " + Constants.BREED_TABLE +
                " WHERE name = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(selectBreed);
            preparedStatement.setString(1, pet.getBreed());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int breedId = resultSet.getInt(Constants.BREED_ID);

                String insertPet = "INSERT INTO " + Constants.PET_TABLE + "(" +
                        Constants.PET_NAME + "," +
                        Constants.PET_OWNER + "," +
                        Constants.PET_BREED + ")" +
                        "VALUES(?,?,?);";

                preparedStatement = dbConnector.prepareStatement(insertPet, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, pet.getName());
                preparedStatement.setInt(2, pet.getOwnerId());
                preparedStatement.setInt(3, breedId);

                preparedStatement.execute();

                ResultSet generatedId = preparedStatement.getGeneratedKeys();
                generatedId.next();

                int id = generatedId.getInt(1);
                pet.setPetId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public void deletePet(int petId) {
        String deleteQuery = "DELETE FROM " + Constants.PET_TABLE +
                " WHERE " + Constants.PET_ID + " = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, petId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
