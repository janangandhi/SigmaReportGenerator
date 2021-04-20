package com.scu.srg.model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Project {
    String name;
    List<Task> tasks;
    ProjectStatus status;

    public Project() {
        this.tasks = new ArrayList<>();
        this.status = ProjectStatus.IN_PROGRESS;
    }
}



