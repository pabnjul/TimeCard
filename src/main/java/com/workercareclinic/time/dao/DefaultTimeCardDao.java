package com.workercareclinic.time.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.entity.Stamp;
import com.workercareclinic.time.entity.StampChoice;
import com.workercareclinic.time.entity.TimeSchedule;

@Component
public class DefaultTimeCardDao implements TimeCardDao {
  ZoneId zone = ZoneId.systemDefault();
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  // removed correction--add later
  @Override
  public TimeSchedule saveTimeSchedule(Employee employee, LocalDate clockDate,
      LocalTime clockTime) {
    SqlParams params = generateInsertSql(employee, clockDate, clockTime);

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(params.sql, params.source, keyHolder);
    clockTime = LocalTime.now().truncatedTo(ChronoUnit.MINUTES);
    clockDate = LocalDate.now();

    Long scheduleID = keyHolder.getKey().longValue();
//@formatter:off
    return TimeSchedule.builder()
        .scheduleID(scheduleID)
        .employee(employee)
        .clockDate(clockDate)
        .clockTime(clockTime)
        .build();
//@formatter:on
  }
//changed to public?
  public void saveStamp(List<Stamp> stampChoices, Long scheduleID) {
    for (Stamp choice : stampChoices) {

      SqlParams params = generateInsertSql(choice, scheduleID);
      jdbcTemplate.update(params.sql, params.source);

    }
  }
  
  private SqlParams generateInsertSql(Stamp stampID, Long scheduleID) {
    SqlParams params = new SqlParams();

  //@formatter:off
  params.sql = ""
      + "INSERT INTO stampSchedule ("
      + "scheduleID, stampID"
      + ") VALUES ("
      + ":scheduleID, :stampID"
      + ")";
  //@formatter:on

    params.source.addValue("scheduleID", scheduleID);
    params.source.addValue("stampID", stampID.getStampID());

    return params;
  }
  
  private SqlParams generateInsertSql(Employee employee, LocalDate clockDate, LocalTime clockTime) {
    SqlParams params = new SqlParams();

    //@formatter:off
    params.sql = "" 
        + "INSERT INTO timeSchedule ("
        + "employee, clockDate, clockTime"
        + ") VALUES ("
        + ":employee, :clockDate, :clockTime"
        + ")";
      
    //@formatter:on
    params.source.addValue("employee", employee);

    return params;
  }

  // removed stampCorrect for the time being
  @Override
  public Stamp fetchstamp(Stamp stamp) {

   //@formatter:off
   String sql = ""
       + "SELECT * "
       + "FROM stamp "
       + "WHERE stampID = :stampID";
   //@formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("stampID", stamp);

    return jdbcTemplate.query(sql, params, new StampResultSetExtractor()); 

  }

  // round clock time to nearest quarter hour
  public double diffToNearestQuarter(LocalTime time1, LocalTime time2) {

    long timeDifference = time1.until(time2, ChronoUnit.MINUTES);
    int hours = (int) (timeDifference / 60);
    int time = (int) (timeDifference % 60);
    int remainder = time % 15;
    int quarters = time / 15;
    double total = 0;
    if (remainder != 0) {
      if (quarters == 3 & remainder > 6) {
        System.out.println("Total time is " + (hours + 1) + " hours");
      } else if (remainder >= 6) {
        total = hours + ((quarters + 1) * 25 / 100);
        System.out
            .println("Total time is " + hours + " hours and " + ((quarters + 1) * 15) + " minutes");
      } else {
        total = hours + (quarters * 25 / 100);
        System.out.println("Total time is " + hours + " hours and " + (quarters * 15) + " minutes");
      }
    } else {
      total = hours;
      System.out.println("Total time is " + hours + " hours");
    }
    return total;

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



  // for selected time period of Sunday through Saturday,
  // if clock in follows clock-out, set clock in to time 1 and clock out to time 2
  // and add totals through the end of the time period; Otherwise, missed stamp
  // must start with clock in
  @Override
  public void fetchTimes(Employee employee, List<TimeSchedule> timeSchedule,
      LocalDate ppDate) {
    LocalTime inTime = null;
    LocalTime outTime = null;
    double tcTotal = 0;

    for (TimeSchedule times : timeSchedule) {
      if (times.getClockDate().isAfter(findSunday(ppDate))
          && times.getClockDate().isBefore(findSaturday(ppDate))) {
        while (times.getEmployee().equals(employee)) {
  //double-check whether this goes through
          if (times.getStamps().equals(StampChoice.IN)) {
            inTime = times.getClockTime();
          } else if (times.getStamps().equals(StampChoice.OUT)) {
            outTime = times.getClockTime();
          }
          tcTotal += diffToNearestQuarter(inTime, outTime);
        }
        System.out.println("For the payperiod starting " + findSunday(ppDate)
            + " the total hours worked are " + tcTotal + " for the employee " + employee);
        // possibly print out results for later use
        // System.setOut(null);
      }
    }
  }



  class StampResultSetExtractor implements ResultSetExtractor<Stamp> {
    @Override
    public Stamp extractData(ResultSet rs) throws SQLException {
      rs.next();
      
      //@formatter:off
      return Stamp.builder()
          .stampID(rs.getLong("stampID"))
          .stampChoice(StampChoice.valueOf(rs.getString("stampChoice")))          
          .build();
    }
  }
  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();

  }
  
}
