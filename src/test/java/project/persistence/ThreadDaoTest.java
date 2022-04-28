package project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.Card;
import project.entity.Reply;
import project.entity.Thread;
import project.entity.User;
import project.entity.Reply;
import project.test.util.Database;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThreadDaoTest {
    GenericDao genericDaoThread;
    GenericDao genericDaoUser;
    GenericDao genericDaoReply;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDaoThread = new GenericDao(Thread.class);
        genericDaoUser = new GenericDao(User.class);
        genericDaoReply = new GenericDao(Reply.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all threads
     */
    @Test
    void getAllThreadsSuccess() {
        List<Thread> retrievedThreads = genericDaoThread.getAllEntities();
        assertEquals(6, retrievedThreads.size());
    }

    /**
     * verifies get all threads based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Thread> expectedThreads = genericDaoThread.getAllEntities();
        List<Thread> actualThreads = genericDaoThread.getByPropertyLike("threadTitle","Omnimon is the best");
        assertEquals(1, actualThreads.size());
        assertNotEquals(expectedThreads, actualThreads);
    }

    /**
     * verifies get all threads based on property name and entered value
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Thread> expectedThreads = genericDaoThread.getAllEntities();
        List<Thread> actualThreads = genericDaoThread.getByPropertyEqual("threadTitle","Omnimon is the best");
        assertEquals(1, actualThreads.size());
        assertNotEquals(expectedThreads, actualThreads);
    }

    /**
     * verifies get id of thread
     */
    @Test
    void getByIdSuccess() {
        Thread expectedThread = (Thread) genericDaoThread.getAllEntities().get(0);
        Thread retrievedThread = (Thread) genericDaoThread.getById(1);

        assertNotNull(retrievedThread);
        assertEquals(expectedThread, retrievedThread);
    }

    /**
     * verifies thread update
     */
    @Test
    void updateSuccess() {
        String newThreadTitle = "Alphamon is the best";
        Thread threadToUpdate = (Thread) genericDaoThread.getById(1);
        threadToUpdate.setThreadTitle(newThreadTitle);
        genericDaoThread.saveOrUpdate(threadToUpdate);

        Thread retrievedThread = (Thread) genericDaoThread.getById(1);
        assertEquals(threadToUpdate, retrievedThread);
    }

    /**
     * verifies thread delete
     */
    @Test
    void deleteSuccess() {
        List<Thread> expectedThreads = genericDaoThread.getAllEntities();
        genericDaoThread.delete(genericDaoThread.getById(1));
        List<Thread> actualThreads = genericDaoThread.getAllEntities();

        assertNull(genericDaoThread.getById(1));
        assertNotEquals(expectedThreads, actualThreads);
    }

    /**
     * verifies thread insert
     */
    @Test
    void insertSuccess() {
        List<Thread> threadsAfter;
        List<User> retrievedUsers = genericDaoUser.getAllEntities();
        User retrievedUser = retrievedUsers.get(5);
        Thread threadToInsert = new Thread("X-Antibody is a sleeper build", "example description", 0, 0, LocalDate.now(), retrievedUser);

        genericDaoThread.insert(threadToInsert);
        threadsAfter = genericDaoThread.getAllEntities();
        Thread expectedThread = threadsAfter.get(6);

        assertEquals("matt6", retrievedUser.getUserName());
        assertEquals(6, retrievedUser.getId());
        assertEquals(threadToInsert, expectedThread);
    }

    /**
     * verifies thread deleted with its attached entities
     */
    @Test
    void deleteWithEntitiesSuccess() {
        List<Thread> threadsBefore = genericDaoThread.getAllEntities();
        List<Reply> repliesBefore = genericDaoReply.getAllEntities();

        genericDaoThread.delete(genericDaoThread.getById(1));
        List<Thread> actualThreads = genericDaoThread.getAllEntities();
        List<Reply> actualReplies = genericDaoReply.getAllEntities();

        assertNull(genericDaoThread.getById(1));
        assertNull(genericDaoReply.getById(1));

        assertNotEquals(threadsBefore, actualThreads);
        assertNotEquals(repliesBefore, actualReplies);
    }
}
