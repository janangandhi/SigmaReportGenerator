package com.scu.srg.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReportProjectData {

    String projectId;
    int noOfTasks;
    LocalDate startDate;
    LocalDate completedDate;
    int noOfStaff;
    int noOfContract;
    String employeeNames;

}
