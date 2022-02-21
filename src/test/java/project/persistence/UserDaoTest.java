package project.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.entity.User;
import project.test.util.Database;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class UserDaoTest {
    UserDao dao;

    @BeforeEach
    void setUp() {
        dao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    /**
     * verifies get all users
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = dao.getAllUsers();
        assertEquals(6, users.size());
    }

    /**
     * verifies get all users based on property name and entered value
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("userName","matt2");
        assertEquals(1, users.size());
    }

    /**
     * verifies get id of user
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(1);
        assertNotNull(retrievedUser);
        assertEquals("matt1", retrievedUser.getUserName());
    }

    /**
     * verifies user update
     */
    @Test
    void updateSuccess() {
        String newFirstName = "Matthew";
        User userToUpdate = dao.getById(1);
        userToUpdate.setFirstName(newFirstName);
        dao.saveOrUpdate(userToUpdate);
        User retrievedUser = dao.getById(1);
        assertEquals(newFirstName, retrievedUser.getFirstName());
    }

    /**
     * verifies user delete
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(1));
        assertNull(dao.getById(1));
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
        dao.insert(userToInsert);
        usersAfter = dao.getAllUsers();
        assertEquals(7, usersAfter.size());
    }
}
