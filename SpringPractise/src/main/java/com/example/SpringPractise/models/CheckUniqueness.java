package com.example.SpringPractise.models;

import java.util.HashMap;

public class CheckUniqueness {
    public static boolean check(HashMap<String, User> users, User user) {
        return users.containsValue(user);
    }

}
