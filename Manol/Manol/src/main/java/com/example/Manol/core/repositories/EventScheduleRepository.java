package com.example.Manol.core.repositories;

import com.example.Manol.core.model.EventSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional()
public interface EventScheduleRepository extends JpaRepository<EventSchedule,Long> {

    @Transactional
    Optional<EventSchedule> findByUserId(Long user_Id);

    @Transactional
    List<EventSchedule> findAllEventsByUserId(Long user_Id);

    @Modifying
    @Transactional
    @Query("delete from EventSchedule where date < curdate() or date is  null")
    void deleteUselessInfo();
}
