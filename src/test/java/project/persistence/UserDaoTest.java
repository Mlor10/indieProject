package project.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.User;
import project.test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserDaoTest {
    GenericDao genericDao;

    @BeforeEach
    void setUp() {
        genericDao = new GenericDao(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all users
     */
    @Test
    void getAllUsersSuccess() {
        List<User> retrievedUsers = genericDao.getAllEntities();
        assertEquals(6, retrievedUsers.size());
    }

    /**
     * verifies get all users based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = genericDao.getAllEntities();
        List<User> actualUsers = genericDao.getByPropertyLike("userName","matt2");
        assertEquals(1, actualUsers.size());
        assertNotEquals(users, actualUsers);
    }

    /**
     * verifies get id of user
     */
    @Test
    void getByIdSuccess() {
        User expectedUser = (User)genericDao.getAllEntities().get(0);
        User retrievedUser = (User)genericDao.getById(1);

        assertNotNull(retrievedUser);
        assertEquals(expectedUser, retrievedUser);
    }

    /**
     * verifies user update
     */
    @Test
    void updateSuccess() {
        String newFirstName = "Matthew";
        User userToUpdate = (User)genericDao.getById(1);
        userToUpdate.setFirstName(newFirstName);
        genericDao.saveOrUpdate(userToUpdate);

        User retrievedUser = (User)genericDao.getById(1);
        assertEquals(userToUpdate, retrievedUser);
    }

    /**
     * verifies user delete
     */
    @Test
    void deleteSuccess() {
        List<User> users = genericDao.getAllEntities();
        genericDao.delete(genericDao.getById(1));
        List<User> actualUsers = genericDao.getAllEntities();

        assertNull(genericDao.getById(1));
        assertNotEquals(users, actualUsers);
    }

    /**
     * verifies user insert
     */
    @Test
    void insertSuccess() {
        List<User> usersAfter;
        User userToInsert = new User();

        userToInsert.setUserName("matt7");
        userToInsert.setUserPassword("pass7");
        userToInsert.setUserEmail("m7@madisoncollege.edu");

        genericDao.insert(userToInsert);
        usersAfter = genericDao.getAllEntities();
        User expectedUser = usersAfter.get(6);

        assertEquals(userToInsert, expectedUser);
    }
}
