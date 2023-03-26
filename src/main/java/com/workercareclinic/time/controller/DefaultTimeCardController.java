package com.workercareclinic.time.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.TimeSchedule;
import com.workercareclinic.time.service.TimeCardService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class DefaultTimeCardController implements TimeCardController {

  @Autowired
  private TimeCardService timeCardService;
  
  @Override
  public TimeSchedule saveTimeSchedule(@Valid Employee employee, LocalDate clockDate,
      LocalTime clockTime) {
    log.debug("Employee= {}", employee, "ClockDate= {}", clockDate, "ClockTime = {}", clockTime);
    return timeCardService.saveTimeSchedule(employee, clockDate, clockTime);  }

}
