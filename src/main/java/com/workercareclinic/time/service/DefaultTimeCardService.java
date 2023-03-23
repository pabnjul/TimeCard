package com.workercareclinic.time.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.workercareclinic.time.dao.TimeCardDao;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.PayPeriod;
import com.workercareclinic.time.entity.TimeCard;
import com.workercareclinic.time.entity.TimeSchedule;
@Service
public class DefaultTimeCardService implements TimeCardService {

  @Autowired
  private TimeCardDao timeCardDao;
  
  @Transactional
  @Override
  public TimeSchedule createTimeCard(TimeCard timeCard) {
    Employee employee = getEmployee(timeCard);
    Object payPeriod = getPayPeriod(timeCard);
    List<TimeSchedule> timeSchedule = getTimeSchedule(timeCard);
    double ptoNeeded = employee.getMinHours()-timeCard.getPpTot();
    return timeCardDao.saveTimeCard(employee, payPeriod,timeSchedule, ptoNeeded);
  }

  
private List<TimeSchedule> getTimeSchedule(TimeCard timeCard) {
  return timeCardDao.fetchTimeSchedule(timeCard.getTimeSchedule());
}
private Object getPayPeriod(TimeCard timeCard) {
  return timeCardDao.fetchPayPeriod(timeCard.getPpCorr(), timeCard.getPpFirst(), timeCard.getPpTot());
  }

  private Employee getEmployee(TimeCard timeCard) {
    return timeCardDao.fetchEmployee(timeCard.getName());

  }

}
