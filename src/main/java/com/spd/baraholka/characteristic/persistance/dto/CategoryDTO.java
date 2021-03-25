package com.spd.baraholka.characteristic.persistance.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class CategoryDTO {
    @NotNull
    @Size(max = 100)
    private final String categoryName;
    @NotNull
    private final Set<String> characteristics;

    public CategoryDTO(String categoryName, Set<String> characteristics) {
        this.categoryName = categoryName;
        this.characteristics = characteristics;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Set<String> getCharacteristics() {
        return characteristics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryDTO that = (CategoryDTO) o;
        return Objects.equals(categoryName, that.categoryName) && Objects.equals(characteristics, that.characteristics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, characteristics);
    }

    @Override
    public String toString() {
        return "CategoryFEDTO{" +
                "categoryName='" + categoryName + '\'' +
                ", characteristics=" + characteristics +
                '}';
    }
}
