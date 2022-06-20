package com.example.Manol.core.controller;

import com.auth0.jwt.algorithms.Algorithm;
import com.example.Manol.core.Service.user.PasswordHash;
import com.example.Manol.core.Service.user.UserService;
import com.example.Manol.core.model.Users;
import com.example.Manol.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.auth0.jwt.JWT;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

import static com.example.Manol.core.Service.login.JWTTokenDecoder.getEmailFromToken;

@RestController
@RequestMapping
@CrossOrigin("http://localhost:3000")
public class LoginTestController {

    @Autowired
    PasswordHash passwordHash;
    @Autowired
    public UserRepository userRepository;

    @Autowired
    UserService userService;

    private String loginPageURL = "http://localhost:3000/";
    private String afterLoginPageURL = "http://google.com";


    @GetMapping("/h")
    public String helloWorld() {
        return "Hello World";
    }


    @GetMapping("/get")
    public List<Users> getUsers() {
        return userRepository.findAll();
    }


//    @GetMapping("/get/user/{email}")
//
//    public Users getUser(@PathVariable("email")String email) {
//            return  userRepository.findUserByEmail(email);
//
//    }

//    @GetMapping("/get/user/{password}")
//    public  Users getUser(@PathVariable("password")String password) {
//        String encodedPass = String.valueOf(passwordHash.doHashing(password));
//        return userRepository.findUserByPassword(encodedPass);
//    }

    @GetMapping("/currentUser/get/{token}")
    public Users getCurrentUser(@PathVariable("token") String token) {
        String email = getEmailFromToken(token);
        Users user = userRepository.findUserByEmail(email);
        System.out.println(user);
        return user;

    }

    @GetMapping("/get/user/{email}&{password}")
    public String login(@PathVariable("email") String email, @PathVariable("password") String password) {
        String encodedPass = String.valueOf(passwordHash.doHashing(password));
        Users user = userRepository.findUserByEmailAndPassword(email, encodedPass);
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        if (user != null) {
            String acces_token = JWT.create().withSubject(user.getEmail()).withExpiresAt(new Date(System.currentTimeMillis() + 100 * 60 * 1000)).sign(algorithm);

            System.out.println(acces_token);
            return acces_token;
        }
        return "User Not Found";
    }


    @GetMapping("/restricted")
    public ModelAndView redirect() {
        return new ModelAndView("redirect:" + afterLoginPageURL);
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("redirect:" + loginPageURL);//+loginPageURL);
    }

    @GetMapping("/forgottenPassword/{email}&{password}")
    public void resetUserPassword(@PathVariable("email") String email, @PathVariable("password") String password) {
        String encodedPassword = passwordHash.doHashing(password);
        userService.resetPassword(email, encodedPassword);
    }
}
