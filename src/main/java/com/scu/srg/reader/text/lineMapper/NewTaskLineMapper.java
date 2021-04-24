package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.InputRow;
import com.scu.srg.model.InputRowType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NewTaskLineMapper implements LineMapper {

    private static final Logger logger = LogManager.getLogger(NewTaskLineMapper.class);

    @Override
    public InputRow mapLine(String[] lineFields) {
        logger.debug("Line type: New Task Details");
        InputRow mappedLine = new InputRow();
        mappedLine.setType(InputRowType.NEW_TASK);
        mappedLine.setTask(lineFields[0]);
        mappedLine.setProjectId(lineFields[1]);
        mappedLine.setTaskStartDate(LocalDate.parse(lineFields[2],
                DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        return mappedLine;
    }
}
