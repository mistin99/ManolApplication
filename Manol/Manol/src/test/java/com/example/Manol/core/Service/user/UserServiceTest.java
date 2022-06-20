package com.example.Manol.core.Service.user;

import com.example.Manol.core.Service.token.ConfirmationTokenService;
import com.example.Manol.core.model.UserRole;
import com.example.Manol.core.model.Users;
import com.example.Manol.core.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    PasswordHash passwordHash;
    @Mock
    ConfirmationTokenService confirmationTokenService;
    AutoCloseable autoCloseable;
    UserService userServiceTest;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userServiceTest = new UserService(userRepository, passwordHash, confirmationTokenService);
    }

    @Test
    @Disabled
    void loadUserByUsername() {
        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin@gmail.com";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                "Creedforce!@3",
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );

        //when
        userRepository.save(user);
        userServiceTest.loadUserByUsername("mistin@gmail.com");


        //then

        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);

        verify(userRepository).findUserByEmail(String.valueOf(usersArgumentCaptor.capture()));

        Users capturedUser = usersArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void signUpUser() {
        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin_@abv.bg";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                "Creedforce!@3",
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );

        // when
        userServiceTest.signUpUser(user);

        //then
        ArgumentCaptor<Users> usersArgumentCaptor = ArgumentCaptor.forClass(Users.class);

        verify(userRepository).save(usersArgumentCaptor.capture());

        Users capturedUser = usersArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void enableUser() {
        //when
        userServiceTest.enableUser("mistin@gmail.com");

        //then
        verify(userRepository).enableUser("mistin@gmail.com");
    }

    @Test
    void resetPassword() {
        //when
        userServiceTest.resetPassword("mistin@gmail.com","Creed!@3");

        //then
        verify(userRepository).resetUserPassword("mistin@gmail.com","Creed!@3");
    }
}