package com.example.vetclinic.config;

public class Constants {
    public static final String USER_TABLE = "users";
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "passwrd";
    public static final String USER_ROLE_ID = "role_id";

    public static final String OWNER_TABLE = "owners";
    public static final String OWNER_ID = "id";
    public static final String OWNER_NAME = "name";
    public static final String OWNER_PHONE = "phone_number";
    public static final String OWNER_ADDRESS = "address";
    public static final String OWNER_USER_ID = "user_id";

    public static final String DOCTOR_TABLE = "doctors";
    public static final String DOCTOR_ID = "id";
    public static final String DOCTOR_NAME = "name";
    public static final String DOCTOR_PHONE = "phone_number";
    public static final String DOCTOR_ADDRESS = "address";
    public static final String DOCTOR_USER_ID = "user_id";

    public static final String PET_TABLE = "pets";
    public static final String PET_NAME = "name";
    public static final String PET_OWNER = "owner_id";
    public static final String PET_ID = "id";
    public static final String PET_BREED = "breed_id";

    public static final String BREED_TABLE = "breeds";
    public static final String BREED_NAME = "breed_name";
    public static final String BREED_ID = "id";

    public static final String RECEPTION_ID = "id";
    public static final String RECEPTION_TABLE = "receptions";
    public static final String RECEPTION_DOCTOR_ID = "doctor_id";
    public static final String RECEPTION_OWNER_ID = "owner_id";
    public static final String RECEPTION_PET_ID = "pet_id";
    public static final String RECEPTION_DATE = "in_date";
    public static final String RECEPTION_TIME = "in_time";

    public static final String RECEPTION_DISEASES_TABLE = "reception_diseases";
    public static final String RECEPTION_DISEASES_REC = "reception_id";
    public static final String RECEPTION_DISEASES_DIS = "disease_id";

    public static final String DISEASE_TABLE = "diseases";
}
