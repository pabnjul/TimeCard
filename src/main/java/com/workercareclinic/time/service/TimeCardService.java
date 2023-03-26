package com.workercareclinic.time.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.Stamp;
import com.workercareclinic.time.entity.TimeSchedule;


public interface TimeCardService {


  TimeSchedule saveTimeSchedule(Employee employee, LocalDate clockDate, LocalTime clockTime);

  void saveStamp(List<Stamp> stampChoices, Long scheduleID);

  Stamp fetchstamp(Stamp stamp);

  void fetchTimes(Employee employee, List<TimeSchedule> timeSchedule, LocalDate ppDate);
}
