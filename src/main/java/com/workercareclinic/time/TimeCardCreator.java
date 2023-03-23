package com.workercareclinic.time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.workercareclinic.ComponentScanMarker;

@SpringBootApplication(scanBasePackageClasses = {ComponentScanMarker.class})
public class TimeCardCreator {

  public static void main(String[] args) {
    SpringApplication.run(TimeCardCreator.class, args);
    

  }

}
