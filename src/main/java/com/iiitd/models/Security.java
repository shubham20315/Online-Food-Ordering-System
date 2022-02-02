package com.iiitd.models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Security {

    /**
     * method to compute hash of password
     */
    public static String hash(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());

            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            return s.toString();
        } catch(NoSuchAlgorithmException e){
            return password;
        }

    }
}
