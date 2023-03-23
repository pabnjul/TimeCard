package com.workercareclinic.time.dao;

import java.security.Timestamp;
import java.sql.Date;
import java.util.List;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.PayPeriod;
import com.workercareclinic.time.entity.TimeSchedule;

public interface TimeCardDao {




  TimeSchedule fetchPayPeriod(Employee employee, Date ppFirst);

  List<TimeSchedule> fetchTimeSchedule(List<TimeSchedule> timeSchedule);


  Employee fetchEmployee(String name);

  TimeSchedule saveTimeCard(Employee employee, List<TimeSchedule> timeSchedule, double ptoNeeded);
  



}
