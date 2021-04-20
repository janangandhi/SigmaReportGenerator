package com.scu.srg.reader;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextFileReader {

    private static final Logger logger = LogManager.getLogger(TextFileReader.class);

    public void readInput(String fileName) {

        logger.debug("Reading contents from file " + fileName);

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(logger::debug);
        } catch (Exception e) {
            logger.error("Exception in reading file contents: " + e.getMessage());
        }
    }

}
