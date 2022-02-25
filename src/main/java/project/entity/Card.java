package project.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
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
    private int cardPrice;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;
    @Column(name = "user_id")
    private int userId;

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
    public int getCardPrice() {
        return cardPrice;
    }

    /**
     * Sets card price.
     *
     * @param cardPrice the card price
     */
    public void setCardPrice(int cardPrice) {
        this.cardPrice = cardPrice;
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
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardName='" + cardName + '\'' +
                ", cardDescription='" + cardDescription + '\'' +
                ", cardPrice=" + cardPrice +
                ", id=" + id +
                ", userId=" + userId +
                '}';
    }
}
