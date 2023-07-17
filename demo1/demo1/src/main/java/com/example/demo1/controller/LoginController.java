package com.example.demo1.controller;



import com.example.demo1.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        // Verify OTP logic here
        if (verifyOTP(user.getOtp())) {
            // Perform login logic here
            return "Login successful";
        } else {
            return "Invalid OTP";
        }
    }

    private boolean verifyOTP(String otp) {
        // Verify the OTP against the stored value or external service
        // Return true if OTP is valid, false otherwise
        // You can use a library like Google's OTP or an external service like Twilio for OTP verification
        // Implement the OTP verification logic according to the library or service you choose
        return true;
    }
}
