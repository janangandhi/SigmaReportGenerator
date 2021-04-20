package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.TextRow;

import java.util.HashMap;

public class LineMapperFactory {

    HashMap<Integer, LineMapper> mapper;

    public LineMapperFactory() {
        mapper = new HashMap<>();
        mapper.put(2, new CancelledProjectLineMapper());
        mapper.put(3, new NewTaskLineMapper());
        mapper.put(4, new CompletedTaskLineMapper());
        mapper.put(5, new AssignedEmployeeLineMapper());
    }

    public TextRow mapLine(String[] lineFields) {
        return mapper.get(lineFields.length).mapLine(lineFields);
    }

}
