package com.workercareclinic.time.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Employee{

  private Long employeeId;
  private String name;
  private int minHours;
  private double ptoHours;
  private boolean otEligible;
  
  @JsonIgnore
  public Long getemployeeId() {
    return employeeId;
  }
  
//  @Override
//  public int compareTo(Employee that) {
//  
//  //@formatter:off
//    return Comparator
//        .comparing(Employee::Name)
//        .thenComparing(Employee::getLastName)
//        .compare(this, that);
//    //@formatter:on
//  }


}
