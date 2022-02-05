package project.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class test {
    private final Logger logger = LogManager.getLogger(this.getClass());

    public static void main(String[] args) {
        logInfo();
    }

    public void logInfo() {
        logger.info("This is a message from the logger");
    }
}