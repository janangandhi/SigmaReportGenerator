package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.InputRow;

public interface LineMapper {

    public InputRow mapLine(String[] lineFields);
}
