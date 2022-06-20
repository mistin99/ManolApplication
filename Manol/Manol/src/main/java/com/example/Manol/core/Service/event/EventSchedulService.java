package com.example.Manol.core.Service.event;

import com.example.Manol.core.model.EventSchedule;
import com.example.Manol.core.repositories.EventScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventSchedulService {


    private EventScheduleRepository eventScheduleRepository;

    public String enterEventSchedule(EventSchedule eventSchedule) {
        eventScheduleRepository.deleteUselessInfo();
        eventScheduleRepository.save(eventSchedule);
        return "Event enter successfull";
    }
}
