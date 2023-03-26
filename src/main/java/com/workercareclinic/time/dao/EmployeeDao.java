package com.workercareclinic.time.dao;

import java.util.List;
import com.workercareclinic.time.entity.Employee;

public interface EmployeeDao {

  List<Employee> fetchEmployees(String name);

  void deleteEmployee(String name);

  Employee addEmployee(String name, Double minHours, Double ptoHours,
      boolean otEligible);

  Employee updateEmployee(String name, Employee updatedEmployee);
}
