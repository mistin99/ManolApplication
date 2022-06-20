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
public class EventSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private LocalDate date;

    private Long userId;

    public EventSchedule(String title,String text,LocalDate date, Long userId) {
        this.title = title;
        this.text = text;
        this.date = date;
        this.userId = userId;
    }

}