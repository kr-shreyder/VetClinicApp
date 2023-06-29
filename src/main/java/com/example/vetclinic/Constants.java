package com.example.vetclinic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Constants {
    public static final String USER_TABLE = "users";
    public static final String USER_NAME = "name";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "passwrd";


    public static final String OWNER_TABLE = "owners";
    public static final String OWNER_NAME = "name";
    public static final String OWNER_PHONE = "phone_number";
    public static final String OWNER_ADDRESS = "address";
}
