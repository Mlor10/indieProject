package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Column(name = "email")
    private String userEmail;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "user_image")
    private String userImage;
    @Column(name = "admin_permission")
    private String adminPermission;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Card> cards = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Thread> threads = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Reply> replies = new HashSet<>();

    /**
     * Instantiates a new user
     */
    public User() {
    }

    /**
     * Constructor for a new user
     * @param firstName user firstname
     * @param lastName user lastname
     * @param userName user username
     * @param userEmail user email
     * @param dateOfBirth user date of birth
     * @param userImage user image location/url
     */
    public User(String firstName, String lastName, String userName, String userEmail, LocalDate dateOfBirth, String userImage, String adminPermission) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.userEmail = userEmail;
        this.dateOfBirth = dateOfBirth;
        this.userImage = userImage;
        this.adminPermission = adminPermission;
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
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets user's date of birth
     *
     * @param dateOfBirth user date of birth
     */
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets user image
     *
     * @return the user image
     */
    public String getUserImage() {
        return userImage;
    }

    /**
     * Sets user image
     *
     * @param userImage the user image
     */
    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    /**
     * Gets admin permission
     *
     * @return the admin permission
     */
    public String getAdminPermission() {
        return adminPermission;
    }

    /**
     * Sets admin permission
     *
     * @param adminPermission the admin permission
     */
    public void setAdminPermission(String adminPermission) {
        this.adminPermission = adminPermission;
    }

    /**
     * Gets cards
     *
     * @return the cards
     */
    public Set<Card> getCards() {
        return cards;
    }

    /**
     * Sets cards
     *
     * @param cards the cards
     */
    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }

    /**
     * Add card
     *
     * @param card the card
     */
    public void addCard(Card card) {
        cards.add(card);
        card.setUser(this);
    }

    /**
     * Remove card
     *
     * @param card the card
     */
    public void removeCard(Card card) {
        cards.remove(card);
        card.setUser(null);
    }

    /**
     * Gets the threads
     *
     * @return user's threads
     */
    public Set<Thread> getThreads() {
        return threads;
    }

    /**
     * Sets the threads
     *
     * @param threads threads
     */
    public void setThreads(Set<Thread> threads) {
        this.threads = threads;
    }

    /**
     * Add thread
     *
     * @param thread the thread
     */
    public void addThread(Thread thread) {
        threads.add(thread);
        thread.setUser(this);
    }

    /**
     * Remove thread
     *
     * @param thread the thread
     */
    public void removeThread(Thread thread) {
        threads.remove(thread);
        thread.setUser(null);
    }

    /**
     * Gets replies
     *
     * @return the replies
     */
    public Set<Reply> getReplies() {
        return replies;
    }

    /**
     * Sets replies
     *
     * @param replies the replies
     */
    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    /**
     * Add reply
     *
     * @param reply the reply
     */
    public void addReply(Reply reply) {
        replies.add(reply);
        reply.setUser(this);
    }

    /**
     * Remove reply
     *
     * @param reply the reply
     */
    public void removeReply(Reply reply) {
        replies.remove(reply);
        reply.setUser(null);
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", userImage='" + userImage + '\'' +
                ", adminPermission='" + adminPermission + '\'' +
                ", cards=" + cards +
                ", threads=" + threads +
                ", replies=" + replies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && userName.equals(user.userName)
                && userEmail.equals(user.userEmail)
                && Objects.equals(dateOfBirth, user.dateOfBirth)
                && Objects.equals(userImage, user.userImage)
                && Objects.equals(adminPermission, user.adminPermission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, userEmail, id, dateOfBirth, userImage, adminPermission);
    }
}
