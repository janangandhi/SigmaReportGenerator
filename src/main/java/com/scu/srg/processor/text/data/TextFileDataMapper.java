package com.scu.srg.processor.text.data;

import com.scu.srg.model.*;

import java.util.ArrayList;
import java.util.List;

public class TextFileDataMapper {

    public ReportData mapReportData(List<Project> projectData) {
        ReportData reportData = new ReportData();

        int completedProjects = 0;
        int activeProjects = 0;
        int cancelledProjects = 0;
        List<ReportProjectData> reportProjectData = new ArrayList<>();
        for (Project proj : projectData) {

            switch (proj.getProjectStatus()) {
                case ACTIVE:
                    activeProjects++;
                    break;
                case CANCELLED:
                    cancelledProjects++;
                    break;
                case COMPLETED:
                    reportProjectData.add(mapCompletedProjectData(proj));
                    completedProjects++;
                    break;
            }
        }
        reportData.setNoOfProjectsActive(activeProjects);
        reportData.setNoOfProjectsCancelled(cancelledProjects);
        reportData.setNoOfProjectsCompleted(completedProjects);
        reportData.setCompletedProjects(reportProjectData);
        return reportData;
    }

    private ReportProjectData mapCompletedProjectData(Project project) {
        ReportProjectData projectData = new ReportProjectData();
        projectData.setProjectId(project.getId());
        projectData.setStartDate(project.getStartDate());
        projectData.setCompletedDate(project.getEndDate());
        projectData.setNoOfTasks(project.getTasks().size());

        int staffEmployeeCount = 0;
        int contractEmployeeCount = 0;
        StringBuilder employeeNames = new StringBuilder();
        for (Task task : project.getTasks()) {
            for (Employee employee : task.getAssignees()) {
                switch (employee.getStatus()) {
                    case STAFF:
                        staffEmployeeCount++;
                        break;
                    case CONTRACT:
                        contractEmployeeCount++;
                        break;
                }
                employeeNames.append(employee.getName()).append(", ");
            }
        }

        if (employeeNames.length() > 0) {
            employeeNames.delete(employeeNames.length() - 2, employeeNames.length() - 1);
        }
        projectData.setNoOfStaff(staffEmployeeCount);
        projectData.setNoOfContract(contractEmployeeCount);
        projectData.setEmployeeNames(employeeNames.toString().trim());

        return projectData;
    }


}
