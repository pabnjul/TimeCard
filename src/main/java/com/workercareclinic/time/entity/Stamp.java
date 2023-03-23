package com.workercareclinic.time.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class Stamp {
  private Long stampID;
  private StampChoice stampChoice;
  private Boolean stampCorrect;

  @JsonIgnore
  public Long getStampID() {
    return stampID;
  }
  
}
