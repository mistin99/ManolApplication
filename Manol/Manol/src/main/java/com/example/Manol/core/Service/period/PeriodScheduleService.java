package com.example.Manol.core.Service.period;

import com.example.Manol.core.model.PeriodSchedule;
import com.example.Manol.core.repositories.PeriodScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PeriodScheduleService {

    private PeriodScheduleRepository periodScheduleRepository;

    public String enterPeriodSchedule(PeriodSchedule schedule) {
        schedule.setEndDate();
        schedule.setNextMonthStart();
        periodScheduleRepository.deleteOldRecords(schedule.getUserId());
        periodScheduleRepository.save(schedule);
        return "this works";
    }
}
