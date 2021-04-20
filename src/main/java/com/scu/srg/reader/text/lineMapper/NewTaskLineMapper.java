package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.TextRow;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewTaskLineMapper implements LineMapper {

    private static final Logger logger = LogManager.getLogger(NewTaskLineMapper.class);

    @Override
    public TextRow mapLine(String[] lineFields) {
        logger.debug("Line type: New Task Details");
        TextRow mappedLine = new TextRow();
        mappedLine.setTask(lineFields[0]);
        mappedLine.setProjectId(lineFields[1]);
        mappedLine.setProjectStartDate(LocalDate.parse(lineFields[2],
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return mappedLine;
    }
}
