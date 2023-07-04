package com.example.vetclinic.db;

import com.example.vetclinic.config.Configs;
import com.example.vetclinic.config.Constants;
import com.example.vetclinic.core.models.*;
import com.example.vetclinic.presentation.Service;

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
        String password = Service.hashPass(user.getPassword());
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
        password = Service.hashPass(password);
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

    public User getUserById(int userId) {
        User user = null;
        String select = "SELECT * FROM " + Constants.USER_TABLE +
                " WHERE " + Constants.USER_ID + " = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select);
            preparedStatement.setInt(1, userId);

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

    public void updateUser(User user) {
        String updateQuery = "UPDATE " + Constants.USER_TABLE +
                " SET " + Constants.USER_LOGIN + " = ?, " +
                Constants.USER_PASSWORD + " = ?" +
                " WHERE " + Constants.USER_ID + " = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(updateQuery);
            preparedStatement.setString(1, user.getLogin());

            String hashedPassword = Service.hashPass(user.getPassword());
            preparedStatement.setString(2, hashedPassword);

            preparedStatement.setInt(3, user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public ArrayList<Reception> getReceptionsByDoctorId(int doctorId) {
        ArrayList<Reception> receptions = new ArrayList<>();

        String select1 = "SELECT id, in_date, in_time, owner_id, pet_id " +
                "FROM " + Constants.RECEPTION_TABLE +
                " WHERE doctor_id = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(select1);
            preparedStatement.setInt(1, doctorId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int receptionId = resultSet.getInt(Constants.RECEPTION_ID);
                Date date = resultSet.getDate(Constants.RECEPTION_DATE);
                Time time = resultSet.getTime(Constants.RECEPTION_TIME);
                int ownerId = resultSet.getInt(Constants.RECEPTION_OWNER_ID);
                int petId = resultSet.getInt(Constants.RECEPTION_PET_ID);

                String select2 = "SELECT d.common_name AS disease_name " +
                        "FROM " + Constants.RECEPTION_DISEASES_TABLE + " AS rd " +
                        "INNER JOIN " + Constants.DISEASE_TABLE + " AS d ON rd.disease_id = d.id " +
                        "WHERE rd.reception_id = ?";

                PreparedStatement preparedStatement2 = dbConnector.prepareStatement(select2);
                preparedStatement2.setInt(1, receptionId);

                ResultSet resultSet2 = preparedStatement2.executeQuery();

                ArrayList<String> diseases = new ArrayList<>();

                while (resultSet2.next()) {
                    String diseaseName = resultSet2.getString("disease_name");
                    diseases.add(diseaseName);
                }

                Reception reception = new Reception(receptionId, date, time, doctorId, ownerId, petId, diseases);
                receptions.add(reception);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receptions;
    }


    public Reception createRec(Reception reception) {
        String insertReception = "INSERT INTO " + Constants.RECEPTION_TABLE + "(" +
                Constants.RECEPTION_DATE + "," +
                Constants.RECEPTION_TIME + "," +
                Constants.RECEPTION_OWNER_ID + "," +
                Constants.RECEPTION_PET_ID + "," +
                Constants.RECEPTION_DOCTOR_ID +
                ") VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(insertReception, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, reception.getDate());
            preparedStatement.setTime(2, reception.getTime());
            preparedStatement.setInt(3, reception.getOwnerId());
            preparedStatement.setInt(4, reception.getPetId());
            preparedStatement.setInt(5, reception.getDoctorId());

            preparedStatement.execute();

            ResultSet generatedId = preparedStatement.getGeneratedKeys();
            generatedId.next();

            int receptionId = generatedId.getInt(1);
            reception.setId(receptionId);

            ArrayList<String> diseases = reception.getDiseaseName();

            if (diseases != null && !diseases.isEmpty()) {
                String insertReceptionDisease = "INSERT INTO " + Constants.RECEPTION_DISEASES_TABLE + "(" +
                        Constants.RECEPTION_DISEASES_REC + "," +
                        Constants.RECEPTION_DISEASES_DIS +
                        ") VALUES (?, ?)";

                for (String disease : diseases) {
                    String selectDisease = "SELECT id FROM " + Constants.DISEASE_TABLE + " WHERE common_name = ?";

                    preparedStatement = dbConnector.prepareStatement(selectDisease);
                    preparedStatement.setString(1, disease);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int diseaseId = resultSet.getInt("id");

                        preparedStatement = dbConnector.prepareStatement(insertReceptionDisease);
                        preparedStatement.setInt(1, receptionId);
                        preparedStatement.setInt(2, diseaseId);
                        preparedStatement.execute();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reception;
    }

    public void updateRec(Reception reception) {
        String updateReception = "UPDATE " + Constants.RECEPTION_TABLE + " SET " +
                Constants.RECEPTION_DATE + "= ?, " +
                Constants.RECEPTION_TIME + "= ?, " +
                Constants.RECEPTION_OWNER_ID + "= ?, " +
                Constants.RECEPTION_PET_ID + "= ?, " +
                Constants.RECEPTION_DOCTOR_ID + "= ? " +
                "WHERE " + Constants.RECEPTION_ID + " = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(updateReception);
            preparedStatement.setDate(1, reception.getDate());
            preparedStatement.setTime(2, reception.getTime());
            preparedStatement.setInt(3, reception.getOwnerId());
            preparedStatement.setInt(4, reception.getPetId());
            preparedStatement.setInt(5, reception.getDoctorId());
            preparedStatement.setInt(6, reception.getId());

            preparedStatement.executeUpdate();

            String deleteReceptionDiseases = "DELETE FROM " + Constants.RECEPTION_DISEASES_TABLE +
                    " WHERE " + Constants.RECEPTION_DISEASES_REC + " = ?";
            preparedStatement = dbConnector.prepareStatement(deleteReceptionDiseases);
            preparedStatement.setInt(1, reception.getId());
            preparedStatement.executeUpdate();

            ArrayList<String> diseases = reception.getDiseaseName();

            if (diseases != null && !diseases.isEmpty()) {
                String selectDiseaseId = "SELECT id FROM " + Constants.DISEASE_TABLE +
                        " WHERE common_name = ?";
                String insertReceptionDisease = "INSERT INTO " + Constants.RECEPTION_DISEASES_TABLE + "(" +
                        Constants.RECEPTION_DISEASES_REC + "," +
                        Constants.RECEPTION_DISEASES_DIS +
                        ") VALUES (?, ?)";

                for (String disease : diseases) {
                    preparedStatement = dbConnector.prepareStatement(selectDiseaseId);
                    preparedStatement.setString(1, disease);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        int diseaseId = resultSet.getInt("id");

                        preparedStatement = dbConnector.prepareStatement(insertReceptionDisease);
                        preparedStatement.setInt(1, reception.getId());
                        preparedStatement.setInt(2, diseaseId);

                        preparedStatement.execute();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRec (int recId) {
        String deleteQuery = "DELETE FROM " + Constants.RECEPTION_TABLE +
                " WHERE " + Constants.RECEPTION_ID + " = ?";

        try {
            PreparedStatement preparedStatement = dbConnector.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, recId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
