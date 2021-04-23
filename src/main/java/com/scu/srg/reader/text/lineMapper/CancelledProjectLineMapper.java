package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.ProjectStatus;
import com.scu.srg.model.TextRow;
import com.scu.srg.model.TextRowType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class CancelledProjectLineMapper implements LineMapper {

    private static final Logger logger = LogManager.getLogger(CancelledProjectLineMapper.class);

    @Override
    public TextRow mapLine(String[] lineFields) {
        logger.debug("Line type: Cancelled Project Details");
        TextRow mappedLine = new TextRow();
        mappedLine.setType(TextRowType.CANCELLED_PROJECT);
        mappedLine.setProjectId(lineFields[0]);
        mappedLine.setProjectStatus(ProjectStatus.CANCELLED);
        return mappedLine;
    }
}
