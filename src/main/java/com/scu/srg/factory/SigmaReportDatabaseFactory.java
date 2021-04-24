package com.scu.srg.factory;

import com.scu.srg.processor.SigmaReportProcessor;
import com.scu.srg.processor.database.DatabaseProcessor;
import com.scu.srg.reader.SigmaReportReader;
import com.scu.srg.reader.database.DatabaseReader;
import com.scu.srg.writer.SigmaReportWriter;
import com.scu.srg.writer.database.DatabaseWriter;

public class SigmaReportDatabaseFactory implements SigmaReportFactory{

    private static SigmaReportDatabaseFactory instance;

    private SigmaReportDatabaseFactory(){}

    public static SigmaReportDatabaseFactory getInstance() {
        if (instance == null) {
            instance = new SigmaReportDatabaseFactory();
        }
        return instance;
    }

    @Override
    public SigmaReportReader getSigmaReportReader() {
        return new DatabaseReader();
    }

    @Override
    public SigmaReportProcessor getSigmaReportProcessor() {
        return new DatabaseProcessor();
    }

    @Override
    public SigmaReportWriter getSigmaReportWriter() {
        return new DatabaseWriter();
    }

}
