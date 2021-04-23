package com.scu.srg.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Project {
    String id;
    List<Task> tasks;
    ProjectStatus projectStatus;
    LocalDate startDate;
    LocalDate endDate;

    public Project(String id) {
        this.id = id;
        this.tasks = new ArrayList<>();
        this.projectStatus = ProjectStatus.ACTIVE;
    }
}



