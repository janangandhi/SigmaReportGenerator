package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.TextRow;
import com.scu.srg.model.TextRowType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CompletedTaskLineMapper implements LineMapper {

    private static final Logger logger = LogManager.getLogger(CompletedTaskLineMapper.class);

    @Override
    public TextRow mapLine(String[] lineFields) {
        logger.debug("Line type: Completed Task Details");
        TextRow mappedLine = new TextRow();
        mappedLine.setType(TextRowType.COMPLETED_TASK);
        mappedLine.setTask(lineFields[0]);
        mappedLine.setProjectId(lineFields[1]);
        mappedLine.setTaskStartDate(LocalDate.parse(lineFields[2],
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mappedLine.setTaskEndDate(LocalDate.parse(lineFields[3],
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return mappedLine;
    }
}
