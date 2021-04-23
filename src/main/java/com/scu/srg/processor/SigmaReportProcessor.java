package com.scu.srg.processor;

import com.scu.srg.model.ReportData;
import com.scu.srg.model.TextRow;

import java.util.List;

public interface SigmaReportProcessor {

    ReportData processData(List<TextRow> inputData);
}
