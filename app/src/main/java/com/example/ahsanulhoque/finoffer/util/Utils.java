package com.example.ahsanulhoque.finoffer.util;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {

    public static boolean isEmailValid(String email){
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    //Check password with minimum requirement here(it should be minimum 6 characters)
    public static boolean isPasswordValid(String password){
        return password.length() >= 6;
    }

}
