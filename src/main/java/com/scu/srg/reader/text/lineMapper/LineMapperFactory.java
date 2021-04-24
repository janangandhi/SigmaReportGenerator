package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.TextRow;

import java.util.HashMap;

public class LineMapperFactory {

    HashMap<Integer, LineMapper> map;

    public LineMapperFactory() {
        map = new HashMap<>();
        map.put(2, new CancelledProjectLineMapper());
        map.put(3, new NewTaskLineMapper());
        map.put(4, new CompletedTaskLineMapper());
        map.put(5, new AssignedEmployeeLineMapper());
    }

    public TextRow mapLine(String[] lineFields) {
        return map.get(lineFields.length).mapLine(lineFields);
    }

}
