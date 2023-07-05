package com.example.vetclinic.presentation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Service {
    public static String hashPass(String passw) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert md5 != null;
        byte[] bytes = md5.digest(passw.getBytes());
        StringBuilder hashPass = new StringBuilder();
        for (byte b : bytes) {
            hashPass.append(String.format("%02X", b));
        }
        return hashPass.toString();
    }
}
