package com.scu.srg.processor;

import com.scu.srg.model.InputRow;
import com.scu.srg.model.ReportData;

import java.util.List;

public interface SigmaReportProcessor {

    ReportData processData(List<InputRow> inputData);
}
