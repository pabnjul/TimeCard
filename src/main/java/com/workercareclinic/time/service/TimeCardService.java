package com.workercareclinic.time.service;

import com.workercareclinic.time.entity.TimeCard;
import com.workercareclinic.time.entity.TimeSchedule;


public interface TimeCardService {

  TimeSchedule createTimeCard(TimeCard timeCard);

}
