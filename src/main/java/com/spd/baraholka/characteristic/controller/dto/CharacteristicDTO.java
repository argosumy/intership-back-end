package com.spd.baraholka.characteristic.controller.dto;

import com.spd.baraholka.characteristic.persistance.entities.Category;

import java.util.Objects;

public class CharacteristicDTO {
    private String characteristicName;
    private String characteristicValue;
    private boolean isApproved;
    private Category category;

    public CharacteristicDTO() {
    }

    public CharacteristicDTO(String characteristicName,
                             String characteristicValue,
                             boolean isApproved,
                             Category category) {
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
        this.isApproved = isApproved;
        this.category = category;
    }

    public String getCharacteristicName() {
        return characteristicName;
    }

    public String getCharacteristicValue() {
        return characteristicValue;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CharacteristicDTO that = (CharacteristicDTO) o;
        return isApproved == that.isApproved
                && Objects.equals(characteristicName, that.characteristicName)
                && Objects.equals(characteristicValue, that.characteristicValue)
                && category == that.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(characteristicName, characteristicValue, isApproved, category);
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "characteristicName='" + characteristicName + '\'' +
                ", characteristicValue='" + characteristicValue + '\'' +
                ", isApproved=" + isApproved +
                ", category=" + category +
                '}';
    }
}
