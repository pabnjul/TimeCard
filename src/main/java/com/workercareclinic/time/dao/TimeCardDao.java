package com.workercareclinic.time.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.Stamp;
import com.workercareclinic.time.entity.TimeSchedule;

public interface TimeCardDao {

  TimeSchedule saveTimeSchedule(Employee employee, LocalDate clockDate, LocalTime clockTime);
  Stamp fetchstamp(Stamp stamp);
  void fetchTimes(Employee employee, List<TimeSchedule> timeSchedule,
      LocalDate ppDate);
  void saveStamp(List<Stamp> stampChoices, Long scheduleID);



}
