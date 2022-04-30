package project.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Digimon {

    @JsonProperty("name")
    private String digimonName;

    @JsonProperty("img")
    private String digimonImage;

    @JsonProperty("level")
    private String evolutionStage;


    public String getDigimonName() {
        return digimonName;
    }

    public String getDigimonImage() {
        return digimonImage;
    }

    public String getEvolutionStage() {
        return evolutionStage;
    }

    @Override
    public String toString() {
        return "DigimonResponse{" +
                "digimonName='" + digimonName + '\'' +
                ", digimonImage='" + digimonImage + '\'' +
                ", evolutionStage='" + evolutionStage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digimon digimon = (Digimon) o;
        return Objects.equals(digimonName, digimon.digimonName) && Objects.equals(digimonImage, digimon.digimonImage) && Objects.equals(evolutionStage, digimon.evolutionStage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digimonName, digimonImage, evolutionStage);
    }
}
