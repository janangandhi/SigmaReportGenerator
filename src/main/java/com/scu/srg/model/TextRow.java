package com.scu.srg.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class TextRow {
    String task;
    String projectId;
    LocalDate projectStartDate;
    LocalDate projectEndDate;
    String employeeName;
    String employeeEmail;
    EmployeeType employeeType;
    ProjectStatus projectStatus;
}
