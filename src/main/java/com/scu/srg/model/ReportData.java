package com.scu.srg.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReportData {

    int noOfProjectsCancelled;
    int noOfProjectsCompleted;
    int noOfProjectsActive;
    List<ReportProjectData> completedProjects;

}
