package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * A class to represent a card
 */
@Entity(name = "Card")
@Table(name = "card")
public class Card {
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_description")
    private String cardDescription;
    @Column(name = "price")
    private double cardPrice;
    @Column(name = "card_image")
    private String cardImage;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @ManyToOne
    private User user;

    /**
     * Instantiates a new card
     */
    public Card() {
    }

    /**
     * Constructor for a new card
     * @param cardName card name
     * @param cardDescription card description
     * @param cardPrice card price
     * @param user card's user
     */
    public Card(String cardName, String cardDescription, double cardPrice, String cardImage, User user) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.cardPrice = cardPrice;
        this.cardImage = cardImage;
        this.user = user;
    }

    /**
     * Gets card name.
     *
     * @return the card name
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets card name.
     *
     * @param cardName the card name
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * Gets card description.
     *
     * @return the card description
     */
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * Sets card description.
     *
     * @param cardDescription the card description
     */
    public void setCardDescription(String cardDescription) {
        this.cardDescription = cardDescription;
    }

    /**
     * Gets card price.
     *
     * @return the card price
     */
    public double getCardPrice() {
        return cardPrice;
    }

    /**
     * Sets card price.
     *
     * @param cardPrice the card price
     */
    public void setCardPrice(double cardPrice) {
        this.cardPrice = cardPrice;
    }


    /**
     * Gets card image.
     *
     * @return the card image
     */
    public String getCardImage() {
        return cardImage;
    }

    /**
     * Sets card image.
     *
     * @param cardImage the card image
     */
    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                ", cardDescription='" + cardDescription + '\'' +
                ", cardPrice=" + cardPrice +
                ", cardImage='" + cardImage + '\'' +
                ", id=" + id +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Double.compare(card.cardPrice, cardPrice) == 0 && id == card.id && cardName.equals(card.cardName) && Objects.equals(cardDescription, card.cardDescription) && Objects.equals(cardImage, card.cardImage) && user.equals(card.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardName, cardDescription, cardPrice, cardImage, id, user);
    }
}
