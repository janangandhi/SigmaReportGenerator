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

    public Task(TaskBuilder tb) {
        this.Id = tb.id;
        this.startDate = tb.startDate;
        this.endDate = tb.endDate;
        this.status = tb.status;
        this.assignees = tb.assignees;
        assignees = new ArrayList<>();
    }

    public static class TaskBuilder {

        private final String id;
        private LocalDate startDate;
        private LocalDate endDate;
        private TaskStatus status;
        private List<Employee> assignees;


        public TaskBuilder(String id) {
            this.id = id;
            this.status = TaskStatus.IN_PROGRESS;
        }

        public TaskBuilder taskStartsAt(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public TaskBuilder hasAssignedEmployees(List<Employee> assignees) {
            this.assignees = assignees;
            return this;
        }

        public TaskBuilder taskEndsAt(LocalDate endDate) {
            this.endDate = endDate;
            this.status = TaskStatus.COMPLETED;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }
}
