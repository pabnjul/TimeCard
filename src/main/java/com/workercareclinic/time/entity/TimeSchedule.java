package com.workercareclinic.time.entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSchedule {
  
  
  private Long scheduleID;
  private Employee employee;
  private LocalDate clockDate;
  private LocalTime clockTime;
  private Time correction;
  
  @JsonIgnore
  public Long getScheduleID() {
    return scheduleID;
  }
 
}
