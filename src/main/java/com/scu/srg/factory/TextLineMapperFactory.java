package com.scu.srg.factory;

import com.scu.srg.reader.text.lineMapper.*;

import java.util.HashMap;

public class TextLineMapperFactory implements LineMapperFactory {

    private final HashMap<Integer, LineMapper> map;

    public TextLineMapperFactory() {
        map = new HashMap<>();
        map.put(2, new CancelledProjectLineMapper());
        map.put(3, new NewTaskLineMapper());
        map.put(4, new CompletedTaskLineMapper());
        map.put(5, new AssignedEmployeeLineMapper());
    }

    @Override
    public LineMapper getLineMapper(int fieldCount) {
        return map.get(fieldCount);
    }

}
