package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.TextRow;
import com.scu.srg.model.TextRowType;
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
        mappedLine.setType(TextRowType.NEW_TASK);
        mappedLine.setTask(lineFields[0]);
        mappedLine.setProjectId(lineFields[1]);
        mappedLine.setTaskStartDate(LocalDate.parse(lineFields[2],
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return mappedLine;
    }
}
