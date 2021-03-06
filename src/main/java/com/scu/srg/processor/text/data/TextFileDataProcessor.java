package com.scu.srg.processor.text.data;

import com.scu.srg.model.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextFileDataProcessor {

    private static final Logger logger = LogManager.getLogger(TextFileDataProcessor.class);

    List<Project> projects;

    public List<Project> parseFileData(List<InputRow> inputData) {
        projects = new ArrayList<>();

        for (InputRow inputLine : inputData) {

            String projectId = inputLine.getProjectId();

            if (projects.stream().map(Project::getId).noneMatch(projectId::equals)) {
                logger.debug("Adding new project - " + projectId);
                Project newProject = new Project(projectId);
                projects.add(newProject);
            }

            switch (inputLine.getType()) {
                case NEW_TASK:
                    logger.debug("Appending new task to project");
                    appendNewTask(inputLine);
                    break;
                case COMPLETED_TASK:
                    logger.debug("Updating task " + inputLine.getTask() + " to complete");
                    updateTaskToCompleted(inputLine);
                    break;
                case ASSIGNED_EMPLOYEE:
                    logger.debug("Appending assigned employee to task");
                    appendEmployeesToTask(inputLine);
                    break;
                case CANCELLED_PROJECT:
                    logger.debug("Updating project status to cancelled for Project " + projectId);
                    projects = updateCancelledProject(projectId);
                    break;
            }

        }

        updateProjectDetails();

        return projects;
    }

    private void appendNewTask(InputRow inputLine) {
        Optional<Project> optionalProject = projects.stream()
                .filter(element -> inputLine.getProjectId().equals(element.getId()))
                .findAny();

        optionalProject.ifPresent(project -> project.getTasks()
                .add(new Task.SigmaTaskBuilder(inputLine.getTask())
                        .taskStartsAt(inputLine.getTaskStartDate())
                        .build())
        );
    }

    private void updateTaskToCompleted(InputRow inputLine) {
        Optional<Project> optionalProject = projects.stream()
                .filter(element -> inputLine.getProjectId().equals(element.getId()))
                .findAny();

        optionalProject.ifPresent(project -> {
            int taskIndex = 0;
            boolean foundTask = false;
            for (Task t : project.getTasks()) {
                if (t.getId().equals(inputLine.getTask())) {
                    logger.debug("Task " + t.getId() + " found in project. Updating status and end date");
                    Task completeTask = new Task.SigmaTaskBuilder(t.getId())
                            .taskStartsAt(t.getStartDate())
                            .taskEndsAt(inputLine.getTaskEndDate())
                            .hasAssignedEmployees(t.getAssignees())
                            .build();

                    project.getTasks().set(taskIndex, completeTask);
                    foundTask = true;
                    break;
                }
                taskIndex++;
            }
            if (!foundTask) {
                logger.warn("New task not defined for task id " + inputLine.getTask() + ". Ignoring line");
            }
        });
    }

    private void appendEmployeesToTask(InputRow inputLine) {
        Optional<Project> optionalProject = projects.stream()
                .filter(element -> inputLine.getProjectId().equals(element.getId()))
                .findAny();
        optionalProject.ifPresent(project -> {
            Optional<Task> optionalTask = project.getTasks().stream()
                    .filter(element -> inputLine.getTask().equals(element.getId()))
                    .findAny();

            if (optionalTask.isPresent()) {
                optionalTask.get().getAssignees().add(new Employee(inputLine.getEmployeeName(), inputLine.getEmployeeEmail(), inputLine.getEmployeeType()));
            } else {
                logger.warn("No task found for task id " + inputLine.getTask() + ".  Ignoring line");
            }
        });

    }

    private List<Project> updateCancelledProject(String projectId) {
        return projects.stream().peek(project -> {
            if (project.getId().equals(projectId)) {
                project.setProjectStatus(ProjectStatus.CANCELLED);
            }
        }).collect(Collectors.toList());
    }

    private void updateProjectDetails() {
        for (Project proj : projects) {
            logger.debug("Evaluating and updating project details for Project " + proj.getId());

            if (proj.getProjectStatus() == ProjectStatus.CANCELLED) {
                logger.debug("Project is cancelled. Skipping status update");
                continue;
            }

            List<Task> tasks = proj.getTasks();

            if (tasks.size() == 0) {
                logger.debug("Project has no tasks. Skipping update");
                continue;
            }


            if (tasks.stream().anyMatch(task -> task.getStatus() != TaskStatus.COMPLETED)) {
                logger.debug("One or more tasks in IN PROGRESS state. Project is active");
                continue;
            }

            logger.debug("Since all tasks in project are complete, marking status as completed");
            proj.setProjectStatus(ProjectStatus.COMPLETED);

            LocalDate projectStartDate = tasks.get(0).getStartDate();
            LocalDate projectEndDate = tasks.get(0).getEndDate();

            for (Task t : tasks) {
                if (t.getStartDate().isBefore(projectStartDate)) {
                    projectStartDate = t.getStartDate();
                }

                if (t.getEndDate().isAfter(projectEndDate)) {
                    projectEndDate = t.getEndDate();
                }
            }

            proj.setStartDate(projectStartDate);
            proj.setEndDate(projectEndDate);

        }
    }

}
