package com.example.SpringPractise.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/practise")
//Controller это как сервлеты
public class PractiseController {
    @GetMapping("/hello")
    public  String sayHello(){
        return "Hello,Almas";
    }
}
