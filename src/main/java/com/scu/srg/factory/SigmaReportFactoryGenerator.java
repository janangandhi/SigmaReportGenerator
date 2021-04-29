package com.scu.srg.factory;

import com.scu.srg.constant.Constants;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;

public final class SigmaReportFactoryGenerator {

    private final HashMap<String, SigmaReportFactory> map;
    private static final SigmaReportFactoryGenerator instance = new SigmaReportFactoryGenerator();

    private static final Logger logger = LogManager.getLogger(SigmaReportFactoryGenerator.class);

    private SigmaReportFactoryGenerator() {
        map = new HashMap<>();
        map.put(Constants.TEXT, SigmaReportTextFactory.getInstance());
        map.put(Constants.XML, SigmaReportXMLFactory.getInstance());
        map.put(Constants.DATABASE, SigmaReportDatabaseFactory.getInstance());
    }

    public static SigmaReportFactoryGenerator getInstance() {
        return instance;
    }

    public SigmaReportFactory getSigmaReportFactory(String type) {
        SigmaReportFactory factory = map.get(type.toUpperCase());

        if (factory == null) {
            logger.warn("Type "+type+" not supported. Defaulting to text factory.");
            factory = SigmaReportTextFactory.getInstance();
        }
        return factory;
    }
}
