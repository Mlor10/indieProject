package project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.User;
import project.entity.Card;
import project.entity.Thread;
import project.entity.Reply;
import project.test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserDaoTest {
    GenericDao genericDaoUser;
    GenericDao genericDaoCard;
    GenericDao genericDaoThread;
    GenericDao genericDaoReply;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDaoUser = new GenericDao(User.class);
        genericDaoCard = new GenericDao(Card.class);
        genericDaoThread = new GenericDao(Thread.class);
        genericDaoReply = new GenericDao(Reply.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all users
     */
    @Test
    void getAllUsersSuccess() {
        List<User> retrievedUsers = genericDaoUser.getAllEntities();
        assertEquals(6, retrievedUsers.size());
    }

    /**
     * verifies get all users based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> expectedUsers = genericDaoUser.getAllEntities();
        List<User> actualUsers = genericDaoUser.getByPropertyLike("userName","matt2");
        assertEquals(1, actualUsers.size());
        assertNotEquals(expectedUsers, actualUsers);
    }

    /**
     * verifies get id of user
     */
    @Test
    void getByIdSuccess() {
        User expectedUser = (User) genericDaoUser.getAllEntities().get(0);
        User retrievedUser = (User) genericDaoUser.getById(1);

        assertNotNull(retrievedUser);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * verifies user update
     */
    @Test
    void updateSuccess() {
        String newFirstName = "Matthew";
        User userToUpdate = (User) genericDaoUser.getById(1);
        userToUpdate.setFirstName(newFirstName);
        genericDaoUser.saveOrUpdate(userToUpdate);

        User retrievedUser = (User) genericDaoUser.getById(1);
        assertEquals(userToUpdate, retrievedUser);
    }

    /**
     * verifies user delete
     */
    @Test
    void deleteSuccess() {
        List<User> users = genericDaoUser.getAllEntities();
        genericDaoUser.delete(genericDaoUser.getById(1));
        List<User> actualUsers = genericDaoUser.getAllEntities();

        assertNull(genericDaoUser.getById(1));
        assertNotEquals(users, actualUsers);
    }

    /**
     * verifies user insert
     */
    @Test
    void insertSuccess() {
        List<User> usersAfter;
        User userToInsert = new User(null, null, "matt7", "pass7", "m7@madisoncollege.edu", null);
        logger.info(userToInsert.getId());

        genericDaoUser.insert(userToInsert);
        usersAfter = genericDaoUser.getAllEntities();
        User expectedUser = usersAfter.get(6);

        assertEquals(userToInsert, expectedUser);
    }

    /**
     * verifies user inserted with a card
     */
    @Test
    void insertWithCardSuccess() {
        List<User> usersAfter;
        List<Card> cardsAfter;
        User userToInsert = new User(null, null, "matt7", "pass7", "m7@madisoncollege.edu", null);
        Card cardToInsert = new Card("Beelzemon", "example description", 7.99, userToInsert);
        genericDaoUser.insert(userToInsert);
        genericDaoCard.insert(cardToInsert);

        usersAfter = genericDaoUser.getAllEntities();
        User expectedUser = usersAfter.get(6);
        cardsAfter = genericDaoCard.getAllEntities();
        Card expectedCard = cardsAfter.get(6);

        assertEquals(userToInsert, expectedUser);
        assertEquals(cardToInsert, expectedCard);
    }

    /**
     * verifies user deleted with its attached entities
     */
    @Test
    void deleteWithEntitiesSuccess() {
        List<User> usersBefore = genericDaoUser.getAllEntities();
        List<Card> cardsBefore = genericDaoCard.getAllEntities();
        List<Thread> threadsBefore = genericDaoThread.getAllEntities();
        List<Reply> repliesBefore = genericDaoReply.getAllEntities();

        genericDaoUser.delete(genericDaoUser.getById(1));
        List<User> actualUsers = genericDaoUser.getAllEntities();
        List<Card> actualCards = genericDaoCard.getAllEntities();
        List<Thread> actualThreads = genericDaoThread.getAllEntities();
        List<Reply> actualReplies = genericDaoReply.getAllEntities();

        assertNull(genericDaoUser.getById(1));
        assertNull(genericDaoCard.getById(1));
        assertNull(genericDaoThread.getById(1));
        assertNull(genericDaoReply.getById(1));

        assertNotEquals(usersBefore, actualUsers);
        assertNotEquals(cardsBefore, actualCards);
        assertNotEquals(threadsBefore, actualThreads);
        assertNotEquals(repliesBefore, actualReplies);
    }
}
