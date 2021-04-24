package com.scu.srg.processor.text;

import com.scu.srg.model.*;
import com.scu.srg.processor.text.data.TextFileDataProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class TextFileDataProcessorTest {

    private TextFileDataProcessor processor;

    @Before
    public void setup() {
        processor = new TextFileDataProcessor();
    }

    @Test
    public void testParseFileData_WhenCancelledProject() {
        InputRow cancelledProject = new InputRow(InputRowType.CANCELLED_PROJECT, "", "Proj789", null, null,
                "", "", null, ProjectStatus.CANCELLED);

        List<Project> parsedProjects = processor.parseFileData(Collections.singletonList(cancelledProject));

        Assert.assertEquals(1, parsedProjects.size());
        Assert.assertEquals(cancelledProject.getProjectId(), parsedProjects.get(0).getId());
        Assert.assertEquals(cancelledProject.getProjectStatus(), parsedProjects.get(0).getProjectStatus());
    }

    @Test
    public void testParseFileData_WhenNewTask() {
        LocalDate startDate = LocalDate.of(2021, 4, 10);

        InputRow newTask = new InputRow(InputRowType.NEW_TASK, "TaskABC", "Proj123", startDate, null,
                "", "", null, null);

        List<Project> parsedProjects = processor.parseFileData(Collections.singletonList(newTask));

        Assert.assertEquals(1, parsedProjects.size());
        Project proj = parsedProjects.get(0);
        Assert.assertEquals(newTask.getProjectId(), proj.getId());
        Assert.assertEquals(ProjectStatus.ACTIVE, proj.getProjectStatus());
        Assert.assertEquals(1, proj.getTasks().size());
        Assert.assertEquals(newTask.getTask(), proj.getTasks().get(0).getId());
        Assert.assertEquals(startDate, proj.getTasks().get(0).getStartDate());
    }

    @Test
    public void testParseFileData_WhenCompletedTask() {
        LocalDate startDate = LocalDate.of(2021, 4, 10);
        LocalDate endDate = LocalDate.of(2021, 4, 20);


        InputRow newTask = new InputRow(InputRowType.NEW_TASK, "TaskABC", "Proj123", startDate, null,
                "", "", null, null);
        InputRow completedTask = new InputRow(InputRowType.COMPLETED_TASK, "TaskABC", "Proj123", startDate, endDate,
                "", "", null, null);

        List<Project> parsedProjects = processor.parseFileData(Arrays.asList(newTask, completedTask));

        Assert.assertEquals(1, parsedProjects.size());
        Project proj = parsedProjects.get(0);
        Assert.assertEquals(newTask.getProjectId(), proj.getId());
        Assert.assertEquals(1, proj.getTasks().size());
        Assert.assertEquals(newTask.getTask(), proj.getTasks().get(0).getId());
        Assert.assertEquals(startDate, proj.getTasks().get(0).getStartDate());
        Assert.assertEquals(endDate, proj.getTasks().get(0).getEndDate());

        //Project should also be completed.
        Assert.assertEquals(ProjectStatus.COMPLETED, proj.getProjectStatus());
        Assert.assertEquals(startDate, proj.getStartDate());
        Assert.assertEquals(endDate, proj.getEndDate());

    }

    @Test
    public void testParseFileData_WhenOnlyCompletedTask() {
        LocalDate startDate = LocalDate.of(2021, 4, 10);
        LocalDate endDate = LocalDate.of(2021, 4, 20);

        InputRow completedTask = new InputRow(InputRowType.COMPLETED_TASK, "TaskABC", "Proj123", startDate, endDate,
                "", "", null, null);

        List<Project> parsedProjects = processor.parseFileData(Collections.singletonList(completedTask));

        Assert.assertEquals(1, parsedProjects.size());
        Project proj = parsedProjects.get(0);
        Assert.assertEquals(completedTask.getProjectId(), proj.getId());
        Assert.assertEquals(0, proj.getTasks().size());
    }

    @Test
    public void testParseFileData_WhenAllCompletedTask() {
        LocalDate startDate1 = LocalDate.of(2021, 4, 10);
        LocalDate endDate1 = LocalDate.of(2021, 4, 20);
        LocalDate startDate2 = LocalDate.of(2021, 5, 11);
        LocalDate endDate2 = LocalDate.of(2021, 5, 21);

        InputRow newTask = new InputRow(InputRowType.NEW_TASK, "TaskABC", "Proj123", startDate1, null,
                "", "", null, null);
        InputRow completedTask = new InputRow(InputRowType.COMPLETED_TASK, "TaskABC", "Proj123", startDate1, endDate1,
                "", "", null, null);

        InputRow newTask2 = new InputRow(InputRowType.NEW_TASK, "TaskDEF", "Proj123", startDate2, null,
                "", "", null, null);
        InputRow completedTask2 = new InputRow(InputRowType.COMPLETED_TASK, "TaskDEF", "Proj123", startDate2, endDate2,
                "", "", null, null);

        List<Project> parsedProjects = processor.parseFileData(Arrays.asList(newTask, newTask2, completedTask, completedTask2));

        Assert.assertEquals(1, parsedProjects.size());
        Project proj = parsedProjects.get(0);
        Assert.assertEquals(newTask.getProjectId(), proj.getId());
        Assert.assertEquals(2, proj.getTasks().size());

        //Project should also be completed.
        Assert.assertEquals(ProjectStatus.COMPLETED, proj.getProjectStatus());
        Assert.assertEquals(startDate1, proj.getStartDate());
        Assert.assertEquals(endDate2, proj.getEndDate());

    }

    @Test
    public void testParseFileData_WhenMixedTasks() {
        LocalDate startDate1 = LocalDate.of(2021, 4, 10);
        LocalDate endDate1 = LocalDate.of(2021, 4, 20);
        LocalDate startDate2 = LocalDate.of(2021, 5, 11);

        InputRow newTask = new InputRow(InputRowType.NEW_TASK, "TaskABC", "Proj123", startDate1, null,
                "", "", null, null);
        InputRow completedTask = new InputRow(InputRowType.COMPLETED_TASK, "TaskABC", "Proj123", startDate1, endDate1,
                "", "", null, null);
        InputRow newTask2 = new InputRow(InputRowType.NEW_TASK, "TaskDEF", "Proj123", startDate2, null,
                "", "", null, null);

        List<Project> parsedProjects = processor.parseFileData(Arrays.asList(newTask, newTask2, completedTask));

        Assert.assertEquals(1, parsedProjects.size());
        Project proj = parsedProjects.get(0);
        Assert.assertEquals(newTask.getProjectId(), proj.getId());
        Assert.assertEquals(2, proj.getTasks().size());

        //Project should also be completed.
        Assert.assertEquals(ProjectStatus.ACTIVE, proj.getProjectStatus());

    }

    @Test
    public void testParseFileData_WhenAssignedEmployee() {
        LocalDate startDate = LocalDate.of(2021, 4, 10);
        LocalDate endDate = LocalDate.of(2021, 4, 20);

        InputRow newTask = new InputRow(InputRowType.NEW_TASK, "TaskABC", "Proj123", startDate, null,
                "", "", null, null);
        InputRow assignedEmployee1 = new InputRow(InputRowType.ASSIGNED_EMPLOYEE, "TaskABC", "Proj123",
                null, null, "John Doe", "JohnDoe@test.com", EmployeeType.STAFF, null);
        InputRow assignedEmployee2 = new InputRow(InputRowType.ASSIGNED_EMPLOYEE, "TaskABC", "Proj123",
                null, null, "Jane Doe", "JaneDoe@test.com", EmployeeType.CONTRACT, null);

        List<Project> parsedProjects = processor.parseFileData(Arrays.asList(newTask, assignedEmployee1, assignedEmployee2));

        Assert.assertEquals(1, parsedProjects.size());
        Project proj = parsedProjects.get(0);
        Assert.assertEquals(newTask.getProjectId(), proj.getId());
        Assert.assertEquals(1, proj.getTasks().size());
        Assert.assertEquals(newTask.getTask(), proj.getTasks().get(0).getId());

        List<Employee> employees = proj.getTasks().get(0).getAssignees();
        Assert.assertEquals(2, employees.size());
        Assert.assertEquals("John Doe", employees.get(0).getName());
        Assert.assertEquals("JohnDoe@test.com", employees.get(0).getEmail());
        Assert.assertEquals(EmployeeType.STAFF, employees.get(0).getStatus());
        Assert.assertEquals("Jane Doe", employees.get(1).getName());
        Assert.assertEquals("JaneDoe@test.com", employees.get(1).getEmail());
        Assert.assertEquals(EmployeeType.CONTRACT, employees.get(1).getStatus());
    }

    @Test
    public void testParseFileData_AllScenarios() {
        LocalDate startDate = LocalDate.of(2021, 4, 10);
        LocalDate endDate = LocalDate.of(2021, 4, 20);

        //Project 1
        InputRow project1NewTask = new InputRow(InputRowType.NEW_TASK, "TaskABC", "Proj123", startDate, null,
                "", "", null, null);
        InputRow project1CompletedTask = new InputRow(InputRowType.COMPLETED_TASK, "TaskABC", "Proj123", startDate, startDate.plusDays(5),
                "", "", null, null);
        InputRow project1NewTask2 = new InputRow(InputRowType.NEW_TASK, "TaskDEF", "Proj123", startDate.plusMonths(1), null,
                "", "", null, null);
        InputRow project1CompletedTask2 = new InputRow(InputRowType.COMPLETED_TASK, "TaskDEF", "Proj123", startDate.plusMonths(1), startDate.plusMonths(1).plusDays(1),
                "", "", null, null);

        //Project 2
        InputRow project2NewTask = new InputRow(InputRowType.NEW_TASK, "TaskGHI", "Proj345", startDate, null,
                "", "", null, null);
        InputRow assignedEmployee1 = new InputRow(InputRowType.ASSIGNED_EMPLOYEE, "TaskGHI", "Proj345",
                null, null, "John Doe", "JohnDoe@test.com", EmployeeType.STAFF, null);
        InputRow assignedEmployee2 = new InputRow(InputRowType.ASSIGNED_EMPLOYEE, "TaskGHI", "Proj345",
                null, null, "Jane Doe", "JaneDoe@test.com", EmployeeType.CONTRACT, null);

        //Project 3
        InputRow project3Cancelled = new InputRow(InputRowType.CANCELLED_PROJECT, "", "Proj678", null, null,
                "", "", null, null);

        List<Project> parsedProjects = processor.parseFileData(Arrays.asList(project1NewTask, project1CompletedTask, project1NewTask2, project1CompletedTask2, project1CompletedTask2,
                project2NewTask, assignedEmployee1, assignedEmployee2, project3Cancelled));

        Assert.assertEquals(3, parsedProjects.size());


        Assert.assertEquals(project1CompletedTask.getProjectId(), parsedProjects.get(0).getId());
        Assert.assertEquals(2, parsedProjects.get(0).getTasks().size());
        Assert.assertEquals(ProjectStatus.COMPLETED, parsedProjects.get(0).getProjectStatus());

        Assert.assertEquals(project2NewTask.getProjectId(), parsedProjects.get(1).getId());
        Assert.assertEquals(1, parsedProjects.get(1).getTasks().size());
        Assert.assertEquals(2, parsedProjects.get(1).getTasks().get(0).getAssignees().size());
        Assert.assertEquals(ProjectStatus.ACTIVE, parsedProjects.get(1).getProjectStatus());

        Assert.assertEquals(project3Cancelled.getProjectId(), parsedProjects.get(2).getId());
        Assert.assertEquals(ProjectStatus.CANCELLED, parsedProjects.get(2).getProjectStatus());
    }

}
