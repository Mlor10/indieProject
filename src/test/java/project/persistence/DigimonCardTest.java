package project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.Digimon;
import project.entity.DigimonCard;
import project.utilities.PropertiesLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class DigimonCardTest implements PropertiesLoader {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao genericDaoResponse;
    Properties properties;

    @BeforeEach
    void setUp() {
        genericDaoResponse = new GenericDao(DigimonCard.class);
        properties = loadProperties("/apipath.properties");
    }

    /**
     * verifies get all digimon cards from digimon io api
     * https://documenter.getpostman.com/view/14059948/TzecB4fH
     */
    @Test
    void getResponseWithParamSuccess() {
        String url = properties.getProperty("digimon.card.url.path");
        String param = properties.getProperty("digimon.card.name.param");
        String paramValue = "";
        List<DigimonCard> retrievedCards = genericDaoResponse.getResponseWithParam(url, param, paramValue);
        assertEquals(2103, retrievedCards.size());
    }

    /**
     * verifies converting api response object list to json
     */
    @Test
    void createResponseJsonSuccess() {
        String url = properties.getProperty("digimon.card.url.path");
        String param = properties.getProperty("digimon.card.name.param");
        String paramValue = "MetalGreymon";
        String jsonResponse = null;
        List<DigimonCard> retrievedCards = genericDaoResponse.getResponseWithParam(url, param, paramValue);
        try {
            jsonResponse = genericDaoResponse.createResponseJson(retrievedCards);
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
        String url = properties.getProperty("digimon.card.url.path");
        String param = properties.getProperty("digimon.card.name.param");
        String paramValue = "MetalGreymon";
        String jsonResponse = null;
        List<String> jsonPropertyList = new ArrayList<>();
        List<Digimon> retrievedCards = genericDaoResponse.getResponseWithParam(url, param, paramValue);
        try {
            jsonResponse = genericDaoResponse.createResponseJson(retrievedCards);
            logger.info(jsonResponse);
            jsonPropertyList = genericDaoResponse.getJsonProperty(jsonResponse, "image_url");
            logger.info(jsonPropertyList);
        } catch (Exception e){
            logger.error("Exception converting to json: ", e);
        }
        assertEquals("https://images.digimoncard.io/images/cards/BO-01.jpg", jsonPropertyList.get(0));
    }
}
