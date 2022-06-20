package com.example.Manol.core.controller;

import com.example.Manol.core.Service.period.PeriodScheduleService;
import com.example.Manol.core.model.PeriodSchedule;
import com.example.Manol.core.model.Users;
import com.example.Manol.core.repositories.PeriodScheduleRepository;
import com.example.Manol.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.Manol.core.Service.login.JWTTokenDecoder.getEmailFromToken;

@RestController
@RequestMapping(path = "period")
@CrossOrigin("http://localhost:3000")
public class PeriodScheduleControler {

    @Autowired
   PeriodScheduleService periodService;

    @Autowired
   PeriodScheduleRepository scheduleRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping
    @CrossOrigin
    public String enterPeriodSchedule(@RequestBody PeriodSchedule schedule) {

         return periodService.enterPeriodSchedule(schedule);
    }

    @GetMapping("/get/{token}")
    public Optional<PeriodSchedule> getPeriodSchedule(@PathVariable("token")String token) {
        String email = getEmailFromToken(token);
        Users user = userRepository.findUserByEmail(email);
        System.out.println(user);
        return scheduleRepository.findAllByUserId(user.getId());
    }
}
