package com.workercareclinic.time.dao;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import javax.print.attribute.standard.DateTimeAtCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.mysql.cj.x.protobuf.MysqlxExpect.Open.Condition.Key;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.TimeSchedule;

@Component
public class DefaultTimeCardDao implements TimeCardDao {
  ZoneId zone = ZoneId.systemDefault();
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public TimeSchedule fetchPayPeriod(Employee employee, LocalDate clockDate, LocalTime clockTime,
      Time correction) {
    SqlParams params = generateInsertSql(employee, clockDate, clockTime, correction);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    clockTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    clockDate = LocalDate.now(zone);

    Long scheduleID = keyHolder.getKey().longValue();



    return TimeSchedule.builder().scheduleID(scheduleID).employee(employee).clockDate(clockDate)
        .clockTime(clockTime).correction(correction).build();

    // if the clock date is Sunday to Saturday, add times together
    // while (findSunday(clockDate))


  }

  public void diffToNearestQuarter(LocalTime time1, LocalTime time2) {

    long timeDifference = time1.until(time2, ChronoUnit.MINUTES);
    int hours = (int) (timeDifference / 60);
    int time = (int) (timeDifference % 60);
    int remainder = time % 15;
    int quarters = time / 15;
    if (remainder != 0) {
      if (quarters == 3 & remainder > 6) {
        System.out.println("Total time is " + (hours + 1) + " hours");
      } else if (remainder >= 6) {
        System.out
            .println("Total time is " + hours + " hours and " + (quarters * 15 + 15) + " minutes");
      } else {
        System.out.println("Total time is " + hours + " hours and " + (quarters * 15) + " minutes");
      }
    } else {
      System.out.println("Total time is " + hours + " hours");
    }

  }

  // Find Sunday as the start of the pay period
  private LocalDate findSunday(LocalDate ppClock) {
    LocalDate ppFirst = ppClock.with(WeekFields.of(Locale.US).dayOfWeek(), 1L);

    return ppFirst;

  }
private LocalDate findSaturday(LocalDate ppClock) {
  LocalDate ppLast = ppClock.with(WeekFields.of(Locale.US).dayOfWeek(), 7L);

  return ppLast;
}

  private SqlParams generateInsertSql(Employee employee, Date clock) {
    
    
    return null;
  }

//for selected time period of Sunday through Saturday,
//if clock in follows clock-out, set clock in to time 1 and clock out to time 2
//and add totals through the end of the time period; Otherwise, missed stamp
//must start with clock in
  @Override
  public List<TimeSchedule> fetchTimeSchedule(Employee employee, List<TimeSchedule> timeSchedule, LocalDate ppDate) {
    for (TimeSchedule times : timeSchedule) {
      if (times.getClockDate().isAfter(findSunday(ppDate)) && times.getClockDate().isBefore(findSaturday(ppDate))) {
        if (times.getEmployee().equals(employee)) {
        if(times.get)
      }
    }
    }
    return null;
  }

  @Override
  public Employee fetchEmployee(String name) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PayPeriod saveTimeCard(Employee employee, PayPeriod payPeriod,
      List<TimeSchedule> timeSchedule, double ptoNeeded) {
    // TODO Auto-generated method stub
    return null;
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();

  }

  @Override
  public TimeSchedule saveTimeCard(Employee employee, PayPeriod payPeriod,
      List<TimeSchedule> timeSchedule, double ptoNeeded) {
    // TODO Auto-generated method stub
    return null;
  }

}
