package com.scu.srg.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InputRow {
    InputRowType type;
    String task;
    String projectId;
    LocalDate taskStartDate;
    LocalDate taskEndDate;
    String employeeName;
    String employeeEmail;
    EmployeeType employeeType;
    ProjectStatus projectStatus;
}

