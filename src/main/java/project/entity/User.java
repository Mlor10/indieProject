package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * A class to represent a user
 */
@Entity(name = "User")
@Table(name = "user")
public class User {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name")
    private String userName;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * Instantiates a new user
     */
    public User() {
    }

    /**
     * Gets user's first name
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets user's first name
     *
     * @param firstName user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets user's last name
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets user's last name
     *
     * @param lastName user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user's username
     *
     * @return username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets user's username
     *
     * @param userName user username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Gets user's id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets user's id
     *
     * @param id user id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user's date of birth
     *
     * @return date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets user's date of birth
     *
     * @param dateOfBirth user date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
