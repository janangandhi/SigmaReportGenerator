package com.scu.srg.processor.text;

import com.scu.srg.model.*;
import com.scu.srg.processor.text.data.TextFileDataMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextFileDataMapperTest {

    private TextFileDataMapper mapper;

    @Before
    public void setupTest() {
        mapper = new TextFileDataMapper();
    }

    @Test
    public void testMapReportData() {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Jane Doe", "janedoe@test.com", EmployeeType.CONTRACT));
        employeeList.add(new Employee("John Doe", "johndoe@test.com", EmployeeType.STAFF));
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task.SigmaTaskBuilder("task1")
                .taskStartsAt(LocalDate.now())
                .build());
        tasks.get(0).setAssignees(employeeList);
        Project proj1 = new Project("Proj1");
        proj1.setStartDate(LocalDate.now());
        proj1.setEndDate(LocalDate.now().plusDays(1));
        proj1.setProjectStatus(ProjectStatus.COMPLETED);
        proj1.setTasks(tasks);

        List<Task> tasks2 = new ArrayList<>();
        tasks2.add(new Task.SigmaTaskBuilder("task2")
                .taskStartsAt(LocalDate.now())
                .taskEndsAt(LocalDate.now().plusDays(1))
                .build());
        Project proj2 = new Project("Proj2");
        proj2.setTasks(tasks2);


        Project proj3 = new Project("Proj3");
        proj3.setProjectStatus(ProjectStatus.CANCELLED);

        List<Project> projects = Arrays.asList(proj1, proj2, proj3);

        ReportData reportData = mapper.mapReportData(projects);

        Assert.assertEquals(1, reportData.getNoOfProjectsActive());
        Assert.assertEquals(1, reportData.getNoOfProjectsCancelled());
        Assert.assertEquals(1, reportData.getNoOfProjectsCompleted());

        ReportProjectData data = reportData.getCompletedProjects().get(0);
        Assert.assertEquals("Proj1", data.getProjectId());
        Assert.assertEquals(1, data.getNoOfContract());
        Assert.assertEquals(1, data.getNoOfStaff());
        Assert.assertEquals(1, data.getNoOfTasks());
        Assert.assertEquals(LocalDate.now(), data.getStartDate());
        Assert.assertEquals(LocalDate.now().plusDays(1), data.getCompletedDate());
        Assert.assertEquals("Jane Doe, John Doe", data.getEmployeeNames());
    }
}
