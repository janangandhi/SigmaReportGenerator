package com.scu.srg.reader;

import com.scu.srg.application.App;
import com.scu.srg.application.ReportGenerator;
import com.scu.srg.model.TextRow;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class TextFileReader {

    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    public void readInput(String fileName) {

        logger.debug("Reading contents from file "+ fileName);
        URL resource = ClassLoader.getSystemClassLoader().getResource(fileName);

        logger.debug("URL is: "+resource);
        try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()))) {

            stream.forEach(logger::debug);
        } catch (Exception e) {
            logger.error("Exception in reading file contents: "+ e.getMessage());
        }
    }

}
