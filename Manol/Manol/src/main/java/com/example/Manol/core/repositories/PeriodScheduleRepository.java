package com.example.Manol.core.repositories;

import com.example.Manol.core.model.PeriodSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional()
public interface PeriodScheduleRepository extends JpaRepository<PeriodSchedule,Long> {


    @Transactional
    Optional<PeriodSchedule> findAllByUserId(Long user_id);

    @Modifying
    @Transactional
    @Query("delete from PeriodSchedule  where user_id = ?1")
    void deleteOldRecords(Long user_id);
}
