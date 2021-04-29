package com.scu.srg.application;

import com.scu.srg.builder.SigmaReportBuilder;
import com.scu.srg.constant.Constants;
import com.scu.srg.constant.Properties;
import com.scu.srg.factory.SigmaReportFactory;
import com.scu.srg.factory.SigmaReportFactoryGenerator;

public class App {

    public static void main(String[] args) {

        ReportGenerator reportGenerator = new SigmaReportGenerator(new SigmaReportBuilder());

        SigmaReportFactory reportFactory = SigmaReportFactoryGenerator.getInstance()
                .getReportFactory(Properties.getInstance().getProperty(Constants.FILETYPE_PROPERTY));

        reportGenerator.generateReport(reportFactory);
    }
}