package com.workercareclinic.time.entity;

import lombok.Builder;
import lombok.Data;

//implement stampCorrect feature at a later date

@Data
@Builder

public class Stamp {
  private Long stampID;
  private StampChoice stampChoice;
//  private Boolean stampCorrect;

//  @JsonIgnore
//  public Long getStampID() {
//    return stampID;
//  }
  
}
