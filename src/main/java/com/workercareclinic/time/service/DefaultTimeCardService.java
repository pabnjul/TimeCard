package com.workercareclinic.time.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.workercareclinic.time.dao.TimeCardDao;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.Stamp;
import com.workercareclinic.time.entity.TimeSchedule;

@Service
public class DefaultTimeCardService implements TimeCardService {

  @Autowired
  private TimeCardDao timeCardDao;

  @Transactional
  @Override
  public TimeSchedule saveTimeSchedule(Employee employee, LocalDate clockDate,
      LocalTime clockTime) {


    return timeCardDao.saveTimeSchedule(employee, clockDate, clockTime);
  }

  @Override
  public void saveStamp(List<Stamp> stampChoices, Long scheduleID) {
    timeCardDao.saveStamp(stampChoices, scheduleID);
    
  };

  @Override
  public Stamp fetchstamp(Stamp stamp) {
    // TODO Auto-generated method stub
    return timeCardDao.fetchstamp(stamp);
  }

  @Override
  public void fetchTimes(Employee employee, List<TimeSchedule> timeSchedule, LocalDate ppDate) {
    timeCardDao.fetchTimes(employee, timeSchedule, ppDate);

  }


}
