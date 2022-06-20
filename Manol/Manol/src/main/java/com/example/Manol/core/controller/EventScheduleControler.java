package com.example.Manol.core.controller;

import com.example.Manol.core.Service.event.EventSchedulService;
import com.example.Manol.core.model.EventSchedule;
import com.example.Manol.core.model.Users;
import com.example.Manol.core.repositories.EventScheduleRepository;
import com.example.Manol.core.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.Manol.core.Service.login.JWTTokenDecoder.getEmailFromToken;

@RestController
@RequestMapping(path = "event")
@CrossOrigin("http://localhost:3000")
public class EventScheduleControler {

    @Autowired
    EventSchedulService eventSchedulService;
    @Autowired
    EventScheduleRepository eventScheduleRepository;

    @Autowired
    UserRepository userRepository;


    @PostMapping
    @CrossOrigin
    public String enterEvent(@RequestBody EventSchedule eventSchedule) {
        return eventSchedulService.enterEventSchedule(eventSchedule);
    }



    @GetMapping("/get/{token}")
    public List<EventSchedule> getEvents(@PathVariable("token")String token) {
        String email = getEmailFromToken(token);
        Users user = userRepository.findUserByEmail(email);
        eventScheduleRepository.deleteUselessInfo();
        return eventScheduleRepository.findAllEventsByUserId(user.getId());
    }
}
