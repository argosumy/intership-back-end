package com.spd.baraholka.characteristic.persistance.entities;

import java.util.Objects;

public class Characteristic {
    private final int id;
    private final String categoryName;
    private final String characteristicName;
    private final String characteristicValue;
    private final boolean isApproved;

    public Characteristic(int id,
                          String categoryName,
                          String characteristicName,
                          String characteristicValue,
                          boolean isApproved) {
        this.id = id;
        this.categoryName = categoryName;
        this.characteristicName = characteristicName;
        this.characteristicValue = characteristicValue;
        this.isApproved = isApproved;
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
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
                && isApproved == that.isApproved
                && Objects.equals(categoryName, that.categoryName)
                && Objects.equals(characteristicName, that.characteristicName)
                && Objects.equals(characteristicValue, that.characteristicValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, characteristicName, characteristicValue, isApproved);
    }

    @Override
    public String toString() {
        return "Characteristic{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", characteristicName='" + characteristicName + '\'' +
                ", characteristicValue='" + characteristicValue + '\'' +
                ", isApproved=" + isApproved +
                '}';
    }
}
