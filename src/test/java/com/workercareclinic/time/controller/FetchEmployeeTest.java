package com.workercareclinic.time.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.workercareclinic.time.controller.support.FetchEmployeeTestSupport;
import com.workercareclinic.time.entity.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:flyway/migrations/time_schema.sql",
"classpath:flyway/migrations/time_data.sql"},
config = @SqlConfig(encoding = "utf-8"))
class FetchEmployeeTest extends FetchEmployeeTestSupport {

  @Test
  void testThatEmployeesAreReturnedWhenValidFirstAndLastNamesAreSupplied() {
    //Given: a valid name and uri
     String name = "Dave Schmidt";
     String uri = String.format("%s?name=%s", getBaseUri(), name);
    
     System.out.println(uri);
     //When: a connection is made to the uri
    ResponseEntity <List<Employee>> response = getRestTemplate().exchange(uri, HttpMethod.GET, null,
        new ParameterizedTypeReference<>() {});
//     getRestTemplate().getForEntity(uri, Employee.class);  
     
    //Then: a success status code is returned
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    
//    
//    //AND: the actual list returned is the same as the expected list
//    List<Employee> actual = response.getBody();
//    List<Employee> expected = buildExpected();
//    
//    assertThat(actual).isEqualTo(expected);
  }

}
