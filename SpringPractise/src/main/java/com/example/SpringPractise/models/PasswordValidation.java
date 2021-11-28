package com.example.SpringPractise.models;

public class PasswordValidation {

    public static boolean passwordValidation(String password) {
        boolean valid = true;
        if (password.length() < 8) {
            return false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!password.matches(upperCaseChars)) {
            return false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars )) {
            return false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers )) {
            return false;
        }
        String specialChars = "(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)";
        if (!password.matches(specialChars )) {
            return false;
        }
        return valid;
    }
}

