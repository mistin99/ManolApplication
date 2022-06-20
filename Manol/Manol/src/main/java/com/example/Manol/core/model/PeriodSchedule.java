package com.example.Manol.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class PeriodSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate = LocalDate.now();
    @Column(nullable = false)
    private LocalDate nextMonthStart;

    private Long userId;

    public PeriodSchedule(LocalDate startDate, Long userId) {
        this.startDate = startDate;
        this.endDate = startDate.plusDays(7);
        this.nextMonthStart = endDate.plusDays(28);
        this.userId = userId;
    }

    public void setEndDate() {

        this.endDate = startDate.plusDays(7);
    }
    public void setNextMonthStart() {
        this.nextMonthStart = endDate.plusDays(28);
    }



}
