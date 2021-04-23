package com.scu.srg.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Task {
    String Id;
    LocalDate startDate;
    LocalDate endDate;
    List<Employee> assignees;
    TaskStatus status;


    public Task(String taskId) {
        this.Id = taskId;
        assignees = new ArrayList<>();
    }

    public Task(String taskId, LocalDate taskStartDate) {
        this.Id = taskId;
        this.startDate = taskStartDate;
        assignees = new ArrayList<>();
        this.status = TaskStatus.IN_PROGRESS;
    }

    public Task(String taskId, LocalDate taskStartDate, LocalDate taskEndDate) {
        this.Id = taskId;
        assignees = new ArrayList<>();
        this.startDate = taskStartDate;
        this.endDate = taskEndDate;
        this.status = TaskStatus.COMPLETED;
    }
}
