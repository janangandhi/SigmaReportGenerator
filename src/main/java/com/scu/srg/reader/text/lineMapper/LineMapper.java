package com.scu.srg.reader.text.lineMapper;

import com.scu.srg.model.TextRow;

public interface LineMapper {

    public TextRow mapLine(String[] lineFields);
}
