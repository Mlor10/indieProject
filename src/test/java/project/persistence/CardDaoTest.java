package project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.Card;
import project.entity.User;
import project.test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoTest {
    GenericDao genericDaoCard;
    GenericDao genericDaoUser;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDaoCard = new GenericDao(Card.class);
        genericDaoUser = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all cards
     */
    @Test
    void getAllCardsSuccess() {
        List<Card> retrievedCards = genericDaoCard.getAllEntities();
        assertEquals(6, retrievedCards.size());
    }

    /**
     * verifies get all cards based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Card> expectedCards = genericDaoCard.getAllEntities();
        List<Card> actualCards = genericDaoCard.getByPropertyLike("cardName","Omnimon");
        assertEquals(1, actualCards.size());
        assertNotEquals(expectedCards, actualCards);
    }

    /**
     * verifies get id of card
     */
    @Test
    void getByIdSuccess() {
        Card expectedCard = (Card) genericDaoCard.getAllEntities().get(0);
        Card retrievedCard = (Card) genericDaoCard.getById(1);

        assertNotNull(retrievedCard);
        assertEquals(expectedCard, retrievedCard);
    }

    /**
     * verifies card update
     */
    @Test
    void updateSuccess() {
        String newCardName = "Alphamon";
        Card cardToUpdate = (Card) genericDaoCard.getById(1);
        cardToUpdate.setCardName(newCardName);
        genericDaoCard.saveOrUpdate(cardToUpdate);

        Card retrievedCard = (Card) genericDaoCard.getById(1);
        assertEquals(cardToUpdate, retrievedCard);
    }

    /**
     * verifies card delete
     */
    @Test
    void deleteSuccess() {
        List<Card> expectedCards = genericDaoCard.getAllEntities();
        genericDaoCard.delete(genericDaoCard.getById(1));
        List<Card> actualCards = genericDaoCard.getAllEntities();

        assertNull(genericDaoCard.getById(1));
        assertNotEquals(expectedCards, actualCards);
    }

    /**
     * verifies card insert
     */
    @Test
    void insertSuccess() {
        List<Card> cardsAfter;
        List<User> retrievedUsers = genericDaoUser.getAllEntities();
        User retrievedUser = retrievedUsers.get(5);
        Card cardToInsert = new Card("Beelzemon", "example description", 7.99, retrievedUser);

        genericDaoCard.insert(cardToInsert);
        cardsAfter = genericDaoCard.getAllEntities();
        Card expectedCard = cardsAfter.get(6);

        assertEquals("matt6", retrievedUser.getUserName());
        assertEquals(6, retrievedUser.getId());
        assertEquals(cardToInsert, expectedCard);
    }
}
