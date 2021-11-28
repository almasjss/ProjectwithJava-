 package com.example.SpringPractise.models;

import java.util.Objects;

 public class User {
     private Integer id;
     private String login;
     private String email;
     private String phoneNumber;
     private String password;

     public User(Integer id, String login, String email, String phoneNumber, String password) {
         this.id = id;
         this.login = login;
         this.email = email;
         this.phoneNumber = phoneNumber;
         this.password = password;
     }

     public Integer getId() {
         return id;
     }

     public void setId(Integer id) {
         this.id = id;
     }

     public String getLogin() {
         return login;
     }

     public void setLogin(String login) {
         this.login = login;
     }

     public String getPassword() {
         return password;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public String getEmail() {
         return email;
     }

     public void setEmail(String email) {
         this.email = email;
     }

     public String getPhoneNumber() {
         return phoneNumber;
     }

     public void setPhoneNumber(String phoneNumber) {
         this.phoneNumber = phoneNumber;
     }

     @Override
     public int hashCode() {
         return Objects.hash(login, email, phoneNumber);
     }

     @Override
     public boolean equals(Object obj) {
         if (this == obj) return true;
         if (obj == null || getClass() != obj.getClass()) return false;
         User user = (User) obj;
         return Objects.equals(login, user.login) ||
                 Objects.equals(email, user.email) ||
                 Objects.equals(phoneNumber, user.phoneNumber);

     }
 }