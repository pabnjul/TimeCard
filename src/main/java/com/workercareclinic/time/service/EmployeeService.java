package com.workercareclinic.time.service;

import java.util.List;
import com.workercareclinic.time.entity.Employee;

public interface EmployeeService {

  List<Employee> fetchEmployee(String name);

}
