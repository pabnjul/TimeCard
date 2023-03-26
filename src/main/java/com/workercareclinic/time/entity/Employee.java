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
  private Double minHours;
  private Double ptoHours;
  private boolean otEligible;
  
//removed json ignor to use employeeID for now
    @JsonIgnore
  public Long getemployeeId() {
    return employeeId;
  }
//  
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
