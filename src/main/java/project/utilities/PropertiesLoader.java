package project.utilities;

import java.io.IOException;
import java.util.Properties;

public interface PropertiesLoader {
    default Properties loadProperties(String propertiesFilePath) {
        Properties properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream(propertiesFilePath));
        } catch (IOException ioe) {
            System.out.println("Database.loadProperties()...Cannot load the properties file");
            ioe.printStackTrace();
        } catch (Exception e) {
            System.out.println("Database.loadProperties()..." + e);
            e.printStackTrace();
        }
        return properties;
    }
}