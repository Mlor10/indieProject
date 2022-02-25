package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    @Column(name = "password")
    private String userPassword;
    @Column(name = "email")
    private String userEmail;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

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
     * Gets user's password
     * @return password
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * Sets user's password
     * @param userPassword user password
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * Gets user's email
     * @return email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets user's email
     * @param userEmail user email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public Set<Card> getCards() {
        return cards;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    /**
     * Add card.
     *
     * @param card the card
     */
    public void addCard(Card card) {
        cards.add(card);
        card.setUser(this);
    }

    /**
     * Remove card.
     *
     * @param card the card
     */
    public void removeCard(Card card) {
        cards.remove(card);
        card.setUser(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName)
                && userName.equals(user.userName) && userPassword.equals(user.userPassword)
                && userEmail.equals(user.userEmail) && Objects.equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, userPassword, userEmail, id, dateOfBirth);
    }
}
