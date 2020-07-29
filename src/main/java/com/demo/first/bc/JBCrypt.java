package com.demo.first.bc;

import org.mindrot.jbcrypt.BCrypt;

public class JBCrypt {

    public static String bcryptPwd(String pwd,String salt) {

        return BCrypt.hashpw(pwd, salt);
    }

    public static boolean checkPwd(String pwd, String hashed) {
        try {
            return BCrypt.checkpw(pwd, hashed);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
