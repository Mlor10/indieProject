package project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.Digimon;
import project.utilities.PropertiesLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class DigimonTest implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao genericDaoResponse;
    Properties properties;

    @BeforeEach
    void setUp() {
        genericDaoResponse = new GenericDao(Digimon.class);
        properties = loadProperties("/apipath.properties");
    }

    /**
     * verifies get all digimon from digimon api
     * https://digimon-api.herokuapp.com/
     */
    @Test
    void getResponseWithParamSuccess() {
        String url = properties.getProperty("digimon.image.url.path");
        String param = "";
        String paramValue = "";
        List<Digimon> retrievedDigimons = genericDaoResponse.getResponseWithParam(url, param, paramValue);
        assertEquals(209, retrievedDigimons.size());
    }

    /**
     * verifies converting api response object list to json
     */
    @Test
    void createResponseJsonSuccess() {
        String url = properties.getProperty("digimon.image.url.path");
        String param = properties.getProperty("digimon.image.name.param");
        String paramValue = "MetalGreymon";
        String jsonResponse = null;
        List<Digimon> retrievedDigimons = genericDaoResponse.getResponseWithParam(url, param, paramValue);
        try {
            jsonResponse = genericDaoResponse.createResponseJson(retrievedDigimons);
            logger.info(jsonResponse);
        } catch (Exception e){
            logger.error("Exception converting to json: ", e);
        }
        assertNotNull(jsonResponse);
    }

    /**
     * verifies grabbing a json property value from a json object
     */
    @Test
    void getJsonPropertySuccess() {
        String url = properties.getProperty("digimon.image.url.path");
        String param = properties.getProperty("digimon.image.name.param");
        String paramValue = "MetalGreymon";
        String jsonResponse = null;
        List<String> jsonPropertyList = new ArrayList<>();
        List<Digimon> retrievedDigimons = genericDaoResponse.getResponseWithParam(url, param, paramValue);
        try {
            jsonResponse = genericDaoResponse.createResponseJson(retrievedDigimons);
            logger.info(jsonResponse);
            jsonPropertyList = genericDaoResponse.getJsonProperty(jsonResponse, "img");
            logger.info(jsonPropertyList);
        } catch (Exception e){
            logger.error("Exception converting to json: ", e);
        }
        assertEquals("https://digimon.shadowsmith.com/img/metalgreymon.jpg", jsonPropertyList.get(0));
    }
}
