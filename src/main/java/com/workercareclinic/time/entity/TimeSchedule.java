package com.workercareclinic.time.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

//removed correction for now; to implement at a later time
@Data
@Builder
public class TimeSchedule {
  
  
  private Long scheduleID;
  private Employee employee;
  private LocalDate clockDate;
  private LocalTime clockTime;
  private List<Stamp> stamps;
//  private Time correction;
  
  @JsonIgnore
  public Long getScheduleID() {
    return scheduleID;
  }
 
}
