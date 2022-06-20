package com.example.Manol.core.controller;

import com.example.Manol.core.Service.registration.RegistrationRequest;
import com.example.Manol.core.Service.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    @PostMapping
    @CrossOrigin
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/register").toUriString());
        return ResponseEntity.created(uri).body(registrationService.register(request));
    }

    @GetMapping(path = "confirm")
    public ModelAndView confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }


}
