package com.workercareclinic.time.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StampSchedule {
  private TimeSchedule scheduleID;
  private Stamp stampID;
  
}
