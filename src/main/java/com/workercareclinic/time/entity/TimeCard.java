package com.workercareclinic.time.entity;

import java.sql.Date;
import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import lombok.Data;

@Data
public class TimeCard {
 @NotNull
 @Length(max=60)
 @Pattern(regexp = "[\\w\\s]*")
 private String name;
 @NotNull
 @Min(0)
 private Date ppFirst;
 @Positive
 private double ppTot;
 private double ppCorr;
 private List<TimeSchedule> timeSchedule;
 
 
}
