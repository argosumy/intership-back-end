package com.spd.baraholka.characteristic.persistance.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class CategoryDTO {
    @NotNull
    @Size(max = 100)
    private final Category category;
    @NotNull
    private final Set<String> characteristicsName;

    public CategoryDTO(Category category, Set<String> characteristicsName) {
        this.category = category;
        this.characteristicsName = characteristicsName;
    }

    public Category getCategory() {
        return category;
    }

    public Set<String> getCharacteristicsName() {
        return characteristicsName;
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
        return category == that.category && Objects.equals(characteristicsName, that.characteristicsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, characteristicsName);
    }

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "category=" + category +
                ", characteristics=" + characteristicsName +
                '}';
    }
}
