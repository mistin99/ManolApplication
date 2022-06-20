package com.example.Manol.core.repositories;

import com.example.Manol.core.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional()
public interface UserRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Users u SET u.enabled = true WHERE u.email = ?1")
    void enableUser(String email);

    @Modifying
    @Query("delete from ConfirmationToken c where confirmed_at is not null")
    int deleteToken();

    @Modifying
    @Query("UPDATE Users u set u.password = ?2 where u.email = ?1")
    void resetUserPassword(String email,String password);
    @Transactional
    Users findUserByPassword(String password);

    @Transactional
    Users findUserByEmail(String email);
    @Transactional
    Users findUserByEmailAndPassword(String email,String password);


    @Override
    List<Users> findAll();


}