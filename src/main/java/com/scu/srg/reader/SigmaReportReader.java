package com.scu.srg.reader;

import com.scu.srg.model.InputRow;

import java.util.List;

public interface SigmaReportReader {

    List<InputRow> readInput(String sourceName);
}
