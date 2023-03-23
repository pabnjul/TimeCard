package com.workercareclinic.time.controller;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.workercareclinic.time.entity.TimeCard;
import com.workercareclinic.time.entity.TimeSchedule;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated  
@RequestMapping("/orders")
@OpenAPIDefinition(info = @Info(title = "Time Card Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface TimeCardController {

@Operation(
    summary = "Create a time card summary for an employee",
    description = "Returns the completed time card",
    responses = {
        @ApiResponse (
            responseCode = "201", 
            description = "The created time card is returned", 
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = TimeCard.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid", 
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "A time card component was not found with the input criteria", 
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred", 
            content = @Content(mediaType = "application/json"))
    },
//build more into this section  
    parameters = {
       @Parameter(
           name = "timeCardRequest",
           required = true,
           description = "The order as JSON")
    }
    
    )
  
@PostMapping
@ResponseStatus(code = HttpStatus.CREATED)
TimeSchedule createTimeCard (@Valid @RequestBody TimeCard timecard);
  
}
