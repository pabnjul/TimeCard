package com.workercareclinic.time.service;

import java.util.List;
import com.workercareclinic.time.entity.Employee;

public interface EmployeeService {

  List<Employee> fetchEmployee(String name);

  void deleteEmployee(String name);

  Employee addEmployee(String name, Double minHours, Double ptoHours,
      boolean otEligible);

  Employee updateEmployee(String name, Employee updatedEmployee);

}
