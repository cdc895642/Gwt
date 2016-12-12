package com.mySampleApplication.security;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by cdc89 on 11.12.2016.
 */
public class PasswordUtils {
    private static String forSalt="Random_Salt#Value@SPEC|SYMBOLS!@#$%^";

    private static String getSalt(String userName){
        return userName+forSalt;
    }

    public static String getHashPassword(String userName, String password){
        String passwordWithSalt=password+getSalt(userName);
        String md5Hex = DigestUtils.md5Hex(passwordWithSalt);
        return md5Hex;
    }

    public static boolean checkPassword(String userName, String password, String storedPassword){
        String hashPasswordWithSalt=getHashPassword(userName,password);
        if (storedPassword.equals(hashPasswordWithSalt))
            return true;
        return false;
    }
}
