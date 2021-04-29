package com.scu.srg.factory;

import com.scu.srg.reader.text.lineMapper.LineMapper;

public interface LineMapperFactory {

    LineMapper getLineMapper(int fieldCount);
}
