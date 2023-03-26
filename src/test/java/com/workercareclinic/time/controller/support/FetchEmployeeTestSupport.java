package com.workercareclinic.time.controller.support;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.workercareclinic.time.entity.Employee;


public class FetchEmployeeTestSupport extends BaseTest{
  
  protected List<Employee> buildExpected() {

    List<Employee> list = new LinkedList<>();

    //@formatter:off
    list.add(Employee.builder()
    .name("Dave Schmidt")
    .minHours((double) 80)
    .ptoHours(5.65)
    .otEligible(true)
    .build());
    
   list.add(Employee.builder()
       .name("Allie Glenn")
       .minHours((double) 72)
       .ptoHours(14.05)
       .otEligible(true)
    .build());
    //@formatter:on
//   Collections.sort(list);
    return list;
  }

  
  protected void assertErrorMessageValid(Map<String, Object> error, HttpStatus status) {
    //@formatter:off
    assertThat(error)
    .containsKey("message")
    .containsEntry("status code", status.value())
    .containsEntry("uri", "/time")
    .containsKey("timestamp")
    .containsEntry("reason", status.getReasonPhrase());
    //@formatter:on
  }
 

}
