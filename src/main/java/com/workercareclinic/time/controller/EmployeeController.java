package com.workercareclinic.time.controller;

import java.util.List;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.workercareclinic.time.entity.Employee;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@RequestMapping("/employee")
@OpenAPIDefinition(info = @Info(title = "Employee Service"),
    servers = {@Server(url = "http://localhost:8080", description = "Local server.")})

public interface EmployeeController {
  // @formatter: off
  @Operation(summary = "Returns a given Employee",
      description = "Returns a list of Employees given a first and last name",
      responses = {
          @ApiResponse(responseCode = "200", description = "An Employee is returned",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "201", description = "The employee info is saved",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "400", description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
              description = "No Employees were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))

      },
      parameters = {
          @Parameter(name = "name", allowEmptyValue = false, required = true,
              description = "Enter full name (i.e., 'Bruce Banner')"),
})


  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Employee> fetchEmployee(
      @Length(max = 60) @Pattern(regexp = "[\\w\\s]*") @RequestParam(required = true) String name);

  @Operation(summary = "Adds a given Employee",
      description = "Adds an Employee",
      responses = {
          @ApiResponse(responseCode = "200", description = "An Employee is returned",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "201", description = "The employee info is saved",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "400", description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
              description = "No Employees were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))

      },
      parameters = {
          @Parameter(name = "name", allowEmptyValue = false, required = true,
              description = "Enter full name (i.e., 'Bruce Banner')"),
          @Parameter(name = "ptoHours", allowEmptyValue = false, required = false,
              description = "Current available PTO hours"),
          @Parameter(name = "minHours", allowEmptyValue = true, required = false,
              description = "Minimum hours worked per week"),
          @Parameter(name = "otEligible", allowEmptyValue = false, required = false,
              description = "Whether or not eligible for OT")})

  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Employee addEmployee(
      @Length(max = 60) @Pattern(regexp = "[\\w\\s*") @RequestParam(required = true) String name,
      @Length(max = 2) Double minHours, Double ptoHours, boolean otEligible);

  
  @Operation(summary = "Updates a given Employee",
      description = "Updates Employees PTO given a first and last name",
      responses = {
          @ApiResponse(responseCode = "200", description = "Employee is updated",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "201", description = "The employee info is saved",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "400", description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
              description = "No Employees were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))

      },
      parameters = {
          @Parameter(name = "name", allowEmptyValue = false, required = true,
              description = "Enter full name (i.e., 'Bruce Banner')"),
          @Parameter(name = "ptoHours", allowEmptyValue = false, required = false,
              description = "Current available PTO hours"),
})

  
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Employee updateEmployee(
      @Length(max = 60) @Pattern(regexp = "[\\w\\s*") @RequestParam(required = true) String name,
      Employee updatedEmployee);

  
  @Operation(summary = "Deletes a given Employee",
      description = "Deletes an Employee given a first and last name",
      responses = {
          @ApiResponse(responseCode = "200", description = "Employees is deleted",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "201", description = "The employee info is saved",
              content = @Content(mediaType = "application/json",
                  schema = @Schema(implementation = Employee.class))),
          @ApiResponse(responseCode = "400", description = "The request parameters are invalid",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "404",
              description = "No Employees were found with the input criteria",
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", description = "An unplanned error occurred",
              content = @Content(mediaType = "application/json"))

      },
      parameters = {
          @Parameter(name = "name", allowEmptyValue = false, required = true,
              description = "Enter full name (i.e., 'Bruce Banner')"),
 })


  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  void deleteEmployee(@RequestParam String name);
  //@formatter:on 

}
