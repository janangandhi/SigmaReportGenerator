package com.scu.srg.writer.text;

import com.scu.srg.model.ReportData;
import com.scu.srg.model.ReportProjectData;
import com.scu.srg.writer.SigmaReportWriter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TextFileWriter implements SigmaReportWriter {

    private static final Logger logger = LogManager.getLogger(TextFileWriter.class);

    @Override
    public void writeReport(ReportData reportData) {
        logger.info("Writing report in Text file!!");
        String fileData = fileData(reportData);

        try {
            Files.write(Paths.get("ReportData-Output.txt"), fileData.getBytes());
        } catch (Exception e) {
            logger.error("Error in writing file details: " + e.getMessage());
        }
    }

    private String fileData(ReportData reportData) {
        StringBuilder builder = new StringBuilder();

        builder.append("No. of Projects Canceled: ").append(reportData.getNoOfProjectsCancelled()).append("\n\n");
        builder.append("No. of Projects Completed: ").append(reportData.getNoOfProjectsCompleted()).append("\n\n");
        builder.append("No. of Projects Active: ").append(reportData.getNoOfProjectsActive()).append("\n\n");

        builder.append("<-------------------- Completed Project Details -------------------->").append("\n\n");
        for (ReportProjectData completedProjectData : reportData.getCompletedProjects()) {
            builder.append("<---------------------------------------------->").append("\n\n");
            builder.append("Project Id: ").append(completedProjectData.getProjectId()).append("\n\n");
            builder.append("No. of Tasks: ").append(completedProjectData.getNoOfTasks()).append("\n\n");
            builder.append("Start Date: ").append(completedProjectData.getStartDate().toString()).append("\n\n");
            builder.append("Completed Date: ").append(completedProjectData.getCompletedDate().toString()).append("\n\n");
            builder.append("No. of Staff: ").append(completedProjectData.getNoOfStaff()).append("\n\n");
            builder.append("No. of Contract: ").append(completedProjectData.getNoOfContract()).append("\n\n");
            builder.append("List of Employees: ").append(completedProjectData.getEmployeeNames()).append("\n\n");
            builder.append("<---------------------------------------------->").append("\n");
        }

        return builder.toString();
    }
}
