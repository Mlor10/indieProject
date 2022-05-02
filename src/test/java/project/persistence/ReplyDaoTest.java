package project.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.Reply;
import project.entity.Thread;
import project.entity.User;
import project.test.util.Database;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReplyDaoTest {
    GenericDao genericDaoReply;
    GenericDao genericDaoThread;
    GenericDao genericDaoUser;
    private final Logger logger = LogManager.getLogger(this.getClass());

    @BeforeEach
    void setUp() {
        genericDaoReply = new GenericDao(Reply.class);
        genericDaoThread = new GenericDao(Thread.class);
        genericDaoUser = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all replies
     */
    @Test
    void getAllThreadsSuccess() {
        List<Reply> retrievedReplies = genericDaoReply.getAllEntities();
        assertEquals(6, retrievedReplies.size());
    }

    /**
     * verifies get all replies based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<Reply> expectedReplies = genericDaoReply.getAllEntities();
        List<Reply> actualThreads = genericDaoReply.getByPropertyLike("replyTitle","I agree");
        assertEquals(1, actualThreads.size());
        assertNotEquals(expectedReplies, actualThreads);
    }

    /**
     * verifies get all replies based on property name and entered value
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<Reply> expectedReplies = genericDaoReply.getAllEntities();
        List<Reply> actualThreads = genericDaoReply.getByPropertyEqual("replyTitle","I agree");
        assertEquals(1, actualThreads.size());
        assertNotEquals(expectedReplies, actualThreads);
    }

    /**
     * verifies get id of reply
     */
    @Test
    void getByIdSuccess() {
        Reply expectedReply = (Reply) genericDaoReply.getAllEntities().get(0);
        Reply retrievedReply = (Reply) genericDaoReply.getById(1);

        assertNotNull(retrievedReply);
        assertEquals(expectedReply, retrievedReply);
    }

    /**
     * verifies reply update
     */
    @Test
    void updateSuccess() {
        String newReplyTitle = "I disagree";
        Reply replyToUpdate = (Reply) genericDaoReply.getById(1);
        replyToUpdate.setReplyTitle(newReplyTitle);
        genericDaoReply.saveOrUpdate(replyToUpdate);

        Reply retrievedReply = (Reply) genericDaoReply.getById(1);
        assertEquals(replyToUpdate, retrievedReply);
    }

    /**
     * verifies reply delete
     */
    @Test
    void deleteSuccess() {
        List<Reply> expectedReplies = genericDaoReply.getAllEntities();
        genericDaoReply.delete(genericDaoReply.getById(1));
        List<Reply> actualReplies = genericDaoReply.getAllEntities();

        assertNull(genericDaoReply.getById(1));
        assertNotEquals(expectedReplies, actualReplies);
    }

    /**
     * verifies reply insert
     */
    @Test
    void insertSuccess() {
        List<Reply> repliesAfter;
        List<User> retrievedUsers = genericDaoUser.getAllEntities();
        List<Thread> retrievedThreads = genericDaoThread.getAllEntities();
        Thread retrievedThread = retrievedThreads.get(4);
        User retrievedUser = retrievedUsers.get(5);
        Reply replyToInsert = new Reply("Amazing deck guide", "example content", LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), retrievedThread, retrievedUser);

        genericDaoReply.insert(replyToInsert);
        repliesAfter = genericDaoReply.getAllEntities();
        Reply expectedReply = repliesAfter.get(6);

        assertEquals("matt6", retrievedUser.getUserName());
        assertEquals(6, retrievedUser.getId());
        assertEquals("LordKnightmon deck guide", retrievedThread.getThreadTitle());
        assertEquals(5, retrievedThread.getId());
        assertEquals(replyToInsert, expectedReply);
    }
}
