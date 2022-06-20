package com.example.Manol.core.Service.user;

import com.example.Manol.core.Service.token.ConfirmationTokenService;
import com.example.Manol.core.model.ConfirmationToken;
import com.example.Manol.core.model.Users;
import com.example.Manol.core.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
    private final static String userNotFoundMessage = "User with email %s not found.";

    private  UserRepository userRepository;
    private final PasswordHash passwordHash;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow( () ->
                new UsernameNotFoundException(
                        String.format(userNotFoundMessage,email)
                )
        );

    }

    public String signUpUser(Users user) {
        boolean userExists  = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists) {
            throw  new IllegalStateException("Email already taken");
        }

        String encodedPassword = String.valueOf(passwordHash.doHashing(user.getPassword()));
        user.setPassword(encodedPassword);
        user.setThisAge();

        userRepository.save(user);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmToken = new ConfirmationToken(token, LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),user);
        confirmationTokenService.saveConfirmationToken(confirmToken);


        return token;
    }


    public void enableUser(String email) {
        userRepository.deleteToken();
        userRepository.enableUser(email);
    }

    public void resetPassword(String email,String password) {
        userRepository.resetUserPassword(email,password);

    }


}
