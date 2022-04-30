package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DigimonCard {

    @JsonProperty("soure_effect")
    private String sourceEffect;

    @JsonProperty("color")
    private String cardColor;

    @JsonProperty("digi_type")
    private String digiType;

    @JsonProperty("card_sets")
    private List<String> cardSets;

    @JsonProperty("level")
    private int cardLevel;

    @JsonProperty("card_rarity")
    private String cardRarity;

    @JsonProperty("artist")
    private String cardArtist;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("main_effect")
    private String mainEffect;

    @JsonProperty("type")
    private String cardType;

    @JsonProperty("dp")
    private int digimonPower;

    @JsonProperty("stage")
    private String evolutionStage;

    @JsonProperty("set_name")
    private String setName;

    @JsonProperty("play_cost")
    private int playCost;

    @JsonProperty("name")
    private String cardName;

    @JsonProperty("evolution_cost")
    private int evolutionCost;

    @JsonProperty("attribute")
    private String cardAttribute;

    @JsonProperty("cardnumber")
    private String cardNumber;

    public Object getSourceEffect() {
        return sourceEffect;
    }

    public String getCardColor() {
        return cardColor;
    }

    public String getDigiType() {
        return digiType;
    }

    public List<String> getCardSets() {
        return cardSets;
    }

    public int getCardLevel() {
        return cardLevel;
    }

    public String getCardRarity() {
        return cardRarity;
    }

    public String getCardArtist() {
        return cardArtist;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getMainEffect() {
        return mainEffect;
    }

    public String getCardType() {
        return cardType;
    }

    public int getDigimonPower() {
        return digimonPower;
    }

    public String getEvolutionStage() {
        return evolutionStage;
    }

    public String getSetName() {
        return setName;
    }

    public int getPlayCost() {
        return playCost;
    }

    public String getCardName() {
        return cardName;
    }

    public int getEvolutionCost() {
        return evolutionCost;
    }

    public String getCardAttribute() {
        return cardAttribute;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    @Override
    public String toString() {
        return "DigimonCard{" +
                "sourceEffect=" + sourceEffect +
                ", cardColor='" + cardColor + '\'' +
                ", digiType='" + digiType + '\'' +
                ", cardSets=" + cardSets +
                ", cardLevel=" + cardLevel +
                ", cardRarity='" + cardRarity + '\'' +
                ", cardArtist='" + cardArtist + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", mainEffect='" + mainEffect + '\'' +
                ", cardType='" + cardType + '\'' +
                ", digimonPower=" + digimonPower +
                ", evolutionStage='" + evolutionStage + '\'' +
                ", setName='" + setName + '\'' +
                ", playCost=" + playCost +
                ", cardName='" + cardName + '\'' +
                ", evolutionCost=" + evolutionCost +
                ", cardAttribute='" + cardAttribute + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigimonCard that = (DigimonCard) o;
        return cardLevel == that.cardLevel && digimonPower == that.digimonPower && playCost == that.playCost && evolutionCost == that.evolutionCost && Objects.equals(sourceEffect, that.sourceEffect) && Objects.equals(cardColor, that.cardColor) && Objects.equals(digiType, that.digiType) && Objects.equals(cardSets, that.cardSets) && Objects.equals(cardRarity, that.cardRarity) && Objects.equals(cardArtist, that.cardArtist) && Objects.equals(imageUrl, that.imageUrl) && Objects.equals(mainEffect, that.mainEffect) && Objects.equals(cardType, that.cardType) && Objects.equals(evolutionStage, that.evolutionStage) && Objects.equals(setName, that.setName) && Objects.equals(cardName, that.cardName) && Objects.equals(cardAttribute, that.cardAttribute) && Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceEffect, cardColor, digiType, cardSets, cardLevel, cardRarity, cardArtist, imageUrl, mainEffect, cardType, digimonPower, evolutionStage, setName, playCost, cardName, evolutionCost, cardAttribute, cardNumber);
    }
}