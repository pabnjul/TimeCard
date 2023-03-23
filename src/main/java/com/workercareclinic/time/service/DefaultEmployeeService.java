package com.workercareclinic.time.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.workercareclinic.time.dao.EmployeeDao;
import com.workercareclinic.time.entity.Employee;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultEmployeeService implements EmployeeService {

  @Autowired
  private EmployeeDao employeeDao;

  @Transactional
  @Override
  public List<Employee> fetchEmployee(String name) {
    log.info("FetchEmployee was called with name={}", name);
    List<Employee> employees = employeeDao.fetchEmployees(name);

    if (employees.isEmpty()) {
      String msg = String.format("No employees foud with name=%s", name);
      throw new NoSuchElementException(msg);
    }
  

  return employees;
  }}
