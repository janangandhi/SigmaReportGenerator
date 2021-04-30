package com.scu.srg.model;

import java.time.LocalDate;
import java.util.List;

public interface TaskBuilder {
    TaskBuilder taskStartsAt(LocalDate startDate);

    TaskBuilder taskEndsAt(LocalDate startDate);

    TaskBuilder hasAssignedEmployees(List<Employee> assignees);

    Task build();

}
