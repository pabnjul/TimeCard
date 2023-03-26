package com.workercareclinic.time.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.util.NestedServletException;
import com.workercareclinic.time.entity.Employee;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultEmployeeDao implements EmployeeDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Employee> fetchEmployees(String name) {
    log.debug("Employee DAO: name={}", name);
   //@formatter:off
   String sql = ""
       +"SELECT * "
       +"FROM employee "
       +"WHERE name = :name";
  
    //@formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);

    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
       //@formatter:off
       return Employee.builder()
           .name(rs.getString("name"))
           .minHours(rs.getDouble("minHours"))
           .ptoHours(rs.getDouble("ptoHours"))
           .otEligible(rs.getBoolean("otEligible"))
           .build();
       //@formatter:on
      }
    });

  }

  // Should be made inactive or hidden instead of delete for record keeping, but we need a full CRUD
  // right now
  @Override
  public void deleteEmployee(String name) {
    log.debug("Employee DAO: name={}", name);
    //@formatter:off
    String sql = ""
        +"DELETE FROM employee "
        +"WHERE name = :name";
    //@formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);

    jdbcTemplate.update(sql, params);

  }
//POST method
  @Override
  public Employee addEmployee(String name, Double minHours, Double ptoHours,
      boolean otEligible) {
    log.info("DAO: name ={}", name);
    //@formatter:off
    String sql = ""
        + "INSERT INTO employee ("
        + "name, minHours, ptoHours, otEligible"
        + ") VALUES ("
        + ":name, :minHours, :ptoHours, :otEligible"
        + ")";
   
     //@formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("name", name);
    params.put("minHours", minHours);
    params.put("ptoHours", ptoHours);
    params.put("otEligible", otEligible);
    
    jdbcTemplate.update(sql, params);
    
    return (
    //@formatter:off
      Employee.builder()
      .name(name)
      .minHours(minHours)
      .otEligible(otEligible)
      .ptoHours(ptoHours)
      .build());
  //@formatter:on
  }
//Put method
  @Override
  public Employee updateEmployee(String name, Employee updatedEmployee) {
    log.debug("Update Employee DAO: name= {}", name);
  //@formatter:off
    String sql = ""
        + "UPDATE employee SET "
        + "ptoHours = :ptoHours "
        + "WHERE name = :name;"
        + "";
   
     //@formatter:on

    Map<String, Object> params = new HashMap<>();
    params.put("ptoHours", updatedEmployee.getPtoHours());
    params.put("name", name); 
    
    if(jdbcTemplate.update(sql, params) ==0) {
      throw new NoSuchElementException("update failed");
    }
    return 
        //@formatter:off
          Employee.builder()
          .name(name)
          .ptoHours(updatedEmployee.getPtoHours())
          .build();
      //@formatter:on
  }

}
