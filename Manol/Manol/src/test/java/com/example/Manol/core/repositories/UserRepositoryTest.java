package com.example.Manol.core.repositories;

import com.example.Manol.core.model.UserRole;
import com.example.Manol.core.model.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void itShouldFindByEmail() {

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
        userRepository.save(user);
        //when
     Users aBoolean =  userRepository.findUserByEmail(email);
        //then
        assertThat(aBoolean).isEqualTo(user);
    }
    @Test
    void itShouldNotFindUserIfEmailDoesNotExist() {

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
        userRepository.save(user);
        //when
        Users aBoolean =  userRepository.findUserByEmail("pesho@abv.bg");
        //then
        assertThat(aBoolean).isNotEqualTo(user);
    }

    @Test
    void itShouldEnableUser() {
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
       Users users = userRepository.save(user);

        //when
         userRepository.enableUser(email);

         //then
        assertThat(users.getEnabled().equals(true));
    }

    @Test
    void itShouldNotEnableUser() {
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
        Users users = userRepository.save(user);

        //when

        //then
        assertThat(users.getEnabled()).isNotEqualTo(true);
    }


    @Test
    void itShouldFindUserByPassword() {
        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin_@abv.bg";
        String password = "Creedforce!@3";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                password,
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );
        userRepository.save(user);
        //when
        Users aBoolean =  userRepository.findUserByPassword(password);
        //then
        assertThat(aBoolean).isEqualTo(user);
    }

    @Test
    void itShouldNotFindUserByPasswordIfDoesNotExists() {
        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin_@abv.bg";
        String password = "Creedforce!@3";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                password,
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );
        userRepository.save(user);
        //when
        Users aBoolean =  userRepository.findUserByPassword("creedforce!@3");
        //then
        assertThat(aBoolean).isNotEqualTo(user);
    }

    @Test
    void itShouldFindUserByEmail() {
        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin_@abv.bg";
        String password = "Creedforce!@3";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                password,
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );
        user.setThisAge();
        userRepository.save(user);
        //when
        Users aBoolean =  userRepository.findUserByEmail(email);
        //then
        assertThat(aBoolean).isEqualTo(user);
    }

    @Test
    void itShouldFindUserByEmailAndPassword() {

        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin_@abv.bg";
        String password = "Creedforce!@3";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                password,
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );
        user.setThisAge();
        userRepository.save(user);
        //when
        Users aBoolean =  userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        //then
        assertThat(aBoolean).isEqualTo(user);

    }

    @Test
    void findAll() {

        //given
        LocalDate ld = LocalDate.of(1999,5,1);
        String email = "mistin_@abv.bg";
        String password = "Creedforce!@3";
        Users user = new Users(
                "mistin",
                "mistinov",
                email,
                password,
                ld,
                "Sofia",
                UserRole.DEFAULTUSER
        );
        user.setThisAge();
        userRepository.save(user);
        //when
        List aBoolean =  userRepository.findAll();
        //then
        assertThat(aBoolean).isNotEqualTo(user);

    }
}