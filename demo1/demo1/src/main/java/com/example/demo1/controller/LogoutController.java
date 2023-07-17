package com.example.demo1.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    @PostMapping("/logout")
    public String logout() {
        // Perform logout logic here
        return "Logout successful";
    }
}
