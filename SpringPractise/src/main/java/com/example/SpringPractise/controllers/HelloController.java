package com.example.SpringPractise.controllers;

import com.example.SpringPractise.constants.Constants;
import com.example.SpringPractise.helpers.TokenHelper;
import com.example.SpringPractise.models.User;
import com.example.SpringPractise.requests.LoginRequest;
import com.example.SpringPractise.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class HelloController {
    //Аннотация @Autowired отмечает конструктор, поле или метод как требующий автозаполнения инъекцией зависимости Spring.
    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        String result = userService.setUser(user);
        if (result.equals(Constants.NO_VALID_EMAIL)) {
            return new ResponseEntity(Constants.NO_VALID_EMAIL, HttpStatus.BAD_REQUEST);
        }
        if (result.equals(Constants.NO_VALID_PHONE_NUMBER)) {
            return new ResponseEntity(Constants.NO_VALID_PHONE_NUMBER, HttpStatus.BAD_REQUEST);
        }
        if (result.equals(Constants.USER_ALREADY_EXIST)) {
            return new ResponseEntity(Constants.USER_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
        }
        if (result.equals(Constants.PASSWORD_SHOULD_BE_STRONG)) {
            return new ResponseEntity(Constants.PASSWORD_SHOULD_BE_STRONG, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(TokenHelper.getToken(user.getLogin()));
    }

    @GetMapping("/user")
    public ResponseEntity getUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        String login = TokenHelper.getLoginByToken(token);
        if ((userService.getUsers().containsKey(login) && userService.getTokens().containsKey(login) &&
                !userService.getBlackListTokens().containsKey(login))) {
            return ResponseEntity.ok(userService.getUser(login));
        }
        return new ResponseEntity(Constants.NOT_AUTHORIZED, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/auth")
    public ResponseEntity auth(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        if (!userService.getUsers().containsKey(loginRequest.getLogin()) ||
                !loginRequest.getPassword().equals
                        (userService.getUser(loginRequest.getLogin()).getPassword())) {
            return new ResponseEntity(Constants.LOGIN_PASSWORD, HttpStatus.UNAUTHORIZED);
        }
        String token = request.getHeader("token");
        String login = TokenHelper.getLoginByToken(token);
        userService.removeBlackToken(login, token);
        userService.addToken(login, token);
        return ResponseEntity.ok(userService.getUser(login));
    }

    @GetMapping("/sign-out")
    public ResponseEntity signOut(HttpServletRequest request) {
        String token = request.getHeader("token");
        String login = TokenHelper.getLoginByToken(token);
        userService.removeToken(login, token);
        userService.addBlackToken(login, token);
        return ResponseEntity.ok("You signed out!");
    }

    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody User user, HttpServletRequest request) {
        String token = request.getHeader("token");
        String login = TokenHelper.getLoginByToken(token);
        if (!userService.getUsers().containsKey(login) ||
                !userService.getTokens().containsKey(login) ||
                userService.getBlackListTokens().containsKey(login)) {
            return new ResponseEntity(Constants.NOT_AUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
        String result = userService.setUpdateUser(user);
        if (result.equals(Constants.NO_VALID_EMAIL)) {
            return new ResponseEntity(Constants.NO_VALID_EMAIL, HttpStatus.BAD_REQUEST);
        }
        if (result.equals(Constants.NO_VALID_PHONE_NUMBER)) {
            return new ResponseEntity(Constants.NO_VALID_PHONE_NUMBER, HttpStatus.BAD_REQUEST);
        }
        if (result.equals(Constants.PASSWORD_SHOULD_BE_STRONG)) {
            return new ResponseEntity(Constants.PASSWORD_SHOULD_BE_STRONG, HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Your information is updated!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity delete(HttpServletRequest request) {
        String token = request.getHeader("token");
        String login = TokenHelper.getLoginByToken(token);
        if (!userService.getUsers().containsKey(login) &&
                !userService.getTokens().containsKey(login) &&
                userService.getBlackListTokens().containsKey(login)) {
            return new ResponseEntity(Constants.NOT_AUTHORIZED, HttpStatus.UNAUTHORIZED);
        }
        userService.deleteUser(login);
        return ResponseEntity.ok("You account is deleted!");
    }
}




