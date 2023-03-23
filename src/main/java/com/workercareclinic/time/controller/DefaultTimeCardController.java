package com.workercareclinic.time.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.workercareclinic.time.entity.PayPeriod;
import com.workercareclinic.time.entity.TimeCard;
import com.workercareclinic.time.entity.TimeSchedule;
import com.workercareclinic.time.service.TimeCardService;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class DefaultTimeCardController implements TimeCardController {

  @Autowired
  private TimeCardService timeCardService;
  
  @Override
  public TimeSchedule createTimeCard(@Valid TimeCard timeCard) {
    log.debug("TimeCard={}", timeCard);
    return timeCardService.createTimeCard(timeCard);
  }

}
