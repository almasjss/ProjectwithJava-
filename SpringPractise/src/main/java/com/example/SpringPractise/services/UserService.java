package com.example.SpringPractise.services;

import java.util.HashMap;
import java.util.Map;

import com.example.SpringPractise.constants.Constants;
import com.example.SpringPractise.helpers.ValidateHelper;
import com.example.SpringPractise.models.CheckUniqueness;
import com.example.SpringPractise.models.PasswordValidation;
import com.example.SpringPractise.models.User;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private HashMap<String, User> users = new HashMap<>();
    private Map<String, String> tokens = new HashMap<>();
    private Map<String, String> blackListTokens = new HashMap<>();

    public String setUser(User user) {
        if (!ValidateHelper.validateEmail(user.getEmail())) {
            return Constants.NO_VALID_EMAIL;
        }
        if (!ValidateHelper.validatePhoneNumber(user.getPhoneNumber())) {
            return Constants.NO_VALID_PHONE_NUMBER;
        }
        if (users.containsKey(user.getLogin()) || CheckUniqueness.check(users, user)) {
            return Constants.USER_ALREADY_EXIST;
        }
        if (!PasswordValidation.passwordValidation(user.getPassword())) {
            return Constants.PASSWORD_SHOULD_BE_STRONG;
        }
        users.put(user.getLogin(), user);
        return Constants.OK;
    }

    public String setUpdateUser(User user) {
        if (!ValidateHelper.validateEmail(user.getEmail())) {
            return Constants.NO_VALID_EMAIL;
        }
        if (!ValidateHelper.validatePhoneNumber(user.getPhoneNumber())) {
            return Constants.NO_VALID_PHONE_NUMBER;
        }
        if (!PasswordValidation.passwordValidation(user.getPassword())) {
            return Constants.PASSWORD_SHOULD_BE_STRONG;
        }
        users.replace(user.getLogin(), user);
        return Constants.OK;
    }

    public User getUser(String login) {
        return users.get(login);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void deleteUser(String login) {
        users.remove(login);
    }

    public Map<String, String> getTokens() {
        return tokens;
    }

    public void addToken(String login, String token) {
        tokens.put(login, token);
    }

    public void removeToken(String login, String token) {
        tokens.remove(login, token);
    }

    public Map<String, String> getBlackListTokens() {
        return blackListTokens;
    }

    public void addBlackToken(String login, String token) {
        blackListTokens.put(login, token);
    }

    public void removeBlackToken(String login, String token) {
        blackListTokens.remove(login, token);
    }
}
