package com.scu.srg.reader;

import com.scu.srg.model.TextRow;

import java.util.List;

public interface SigmaReportReader {

    List<TextRow> readInput(String sourceName);
}
