package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.EmployeeType;
import com.scu.srg.model.InputRow;
import com.scu.srg.model.InputRowType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AssignedEmployeeLineMapper implements LineMapper {

    private static final Logger logger = LogManager.getLogger(AssignedEmployeeLineMapper.class);

    @Override
    public InputRow mapLine(String[] lineFields) {
        logger.debug("Line type: Assigned Employee Details");
        InputRow mappedLine = new InputRow();
        mappedLine.setType(InputRowType.ASSIGNED_EMPLOYEE);
        mappedLine.setTask(lineFields[0]);
        mappedLine.setProjectId(lineFields[1]);
        mappedLine.setEmployeeName(lineFields[2]);
        mappedLine.setEmployeeEmail(lineFields[3]);
        mappedLine.setEmployeeType(EmployeeType.valueOf(lineFields[4].toUpperCase()));
        return mappedLine;
    }
}
