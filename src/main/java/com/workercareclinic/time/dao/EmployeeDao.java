package com.workercareclinic.time.dao;

import java.util.List;
import com.workercareclinic.time.entity.Employee;

public interface EmployeeDao {

  List<Employee> fetchEmployees(String name);
}
