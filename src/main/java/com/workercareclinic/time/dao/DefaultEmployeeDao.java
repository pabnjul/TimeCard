package com.workercareclinic.time.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import com.workercareclinic.time.entity.Employee;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class DefaultEmployeeDao implements EmployeeDao {
@Autowired
private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Employee> fetchEmployees(String name) {
   log.debug("DAO: name={}", name);
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
           .minHours(rs.getInt("minHours"))
           .ptoHours(rs.getDouble("ptoHours"))
           .otEligible(rs.getBoolean("otEligible"))
           .build();
       //@formatter:on
     }
   });
   
  }

}
