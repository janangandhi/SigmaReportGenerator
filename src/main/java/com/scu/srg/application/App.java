package com.scu.srg.application;

import com.scu.srg.builder.SigmaReportBuilder;

public class App {

    public static void main(String[] args) {
        ReportGenerator reportGenerator = new ReportGenerator(new SigmaReportBuilder());
        reportGenerator.generateReport();
    }
}