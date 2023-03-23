package com.workercareclinic.time.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.workercareclinic.time.entity.Employee;
import com.workercareclinic.time.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultEmployeeController implements EmployeeController {

  @Autowired
  private EmployeeService employeeService;
  
  @Override
  public List<Employee> fetchEmployee(String name) {
   log.info("name={}", name);
    return employeeService.fetchEmployee(name);
  }

}
