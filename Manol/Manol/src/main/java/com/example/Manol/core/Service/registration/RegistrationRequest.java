package com.example.Manol.core.Service.registration;

import com.example.Manol.core.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;
    private final LocalDate dob;
    private final String address;
    private final UserRole userRole;
}
