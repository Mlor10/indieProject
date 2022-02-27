package project.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Properties;

public interface PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    default Properties loadProperties(String propertiesFilePath) {
        Properties properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioe) {
            logger.error("Database.loadProperties()...Cannot load the properties file", ioe);
        } catch (Exception e) {
            logger.error("Database.loadProperties()...", e);
        }
        return properties;
    }
}