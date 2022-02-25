package project.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.Card;
import project.entity.User;
import project.test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CardDaoTest {
    GenericDao genericDao;
    GenericDao genericDaoUser;

    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(Card.class);
        genericDaoUser = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all cards
     */
    @Test
    void getAllCardsSuccess() {
        List<Card> retrievedCards = genericDao.getAllEntities();
        assertEquals(6, retrievedCards.size());
    }

    /**
     * verifies get all cards based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Card> cards = genericDao.getAllEntities();
        List<Card> actualCards = genericDao.getByPropertyLike("cardName","Omnimon");
        assertEquals(1, actualCards.size());
        assertNotEquals(cards, actualCards);
    }

    /**
     * verifies get id of card
     */
    @Test
    void getByIdSuccess() {
        Card expectedCard = (Card)genericDao.getAllEntities().get(0);
        Card retrievedCard = (Card)genericDao.getById(1);

        assertNotNull(retrievedCard);
        assertEquals(expectedCard, retrievedCard);
    }

    /**
     * verifies card update
     */
    @Test
    void updateSuccess() {
        String newCardName = "Alphamon";
        Card cardToUpdate = (Card)genericDao.getById(1);
        cardToUpdate.setCardName(newCardName);
        genericDao.saveOrUpdate(cardToUpdate);

        Card retrievedCard = (Card)genericDao.getById(1);
        assertEquals(cardToUpdate, retrievedCard);
    }

    /**
     * verifies card delete
     */
    @Test
    void deleteSuccess() {
        List<Card> cards = genericDao.getAllEntities();
        genericDao.delete(genericDao.getById(1));
        List<Card> actualCards = genericDao.getAllEntities();

        assertNull(genericDao.getById(1));
        assertNotEquals(cards, actualCards);
    }

    /**
     * verifies card insert
     */
    @Test
    void insertSuccess() {
        List<Card> cardsAfter;
        List<User> retrievedUsers = genericDaoUser.getAllEntities();
        Card cardToInsert = new Card();
        User retrievedUser = retrievedUsers.get(5);

        cardToInsert.setCardName("Beelzemon");
        cardToInsert.setCardDescription("example description");
        cardToInsert.setCardPrice(7.99);
        cardToInsert.setUser(retrievedUser);

        genericDao.insert(cardToInsert);
        cardsAfter = genericDao.getAllEntities();
        Card expectedCard = cardsAfter.get(6);

        assertEquals("matt6", retrievedUser.getUserName());
        assertEquals(6, retrievedUser.getId());
        assertEquals(cardToInsert, expectedCard);
    }
}
