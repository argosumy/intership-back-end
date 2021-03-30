package com.spd.baraholka.characteristic.persistance.entities;

import java.util.Objects;

public class Characteristic {
    private final int id;
    private final int advertisementId;
    private final Category category;
    private final String characteristicName;
    private final String characteristicValue;
    private final boolean isApproved;

    public Characteristic(int id,
                          int advertisementId,
                          Category category,
                          String characteristicName,
                          String characteristicValue,
                          boolean isApproved) {
        this.id = id;
        this.advertisementId = advertisementId;
        this.category = category;
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public Category getCategory() {
        return category;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Characteristic that = (Characteristic) o;
        return id == that.id
                && advertisementId == that.advertisementId
                && isApproved == that.isApproved
                && category == that.category
                && Objects.equals(characteristicName, that.characteristicName)
                && Objects.equals(characteristicValue, that.characteristicValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, advertisementId, category, characteristicName, characteristicValue, isApproved);
    }

    @Override
    public String toString() {
        return "CharacteristicDTO{" +
                "id=" + id +
                ", advertisementId=" + advertisementId +
                ", category=" + category +
                ", characteristicName='" + characteristicName + '\'' +
                ", characteristicValue='" + characteristicValue + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}
