package com.spd.baraholka.advertisement.controller.dto;

import com.spd.baraholka.characteristic.persistance.entities.Category;

import java.util.List;
import java.util.Map;

public class FilterDTO {

    private List<Category> categories;
    private Double minPrice;
    private Double maxPrice;
    private List<String> cities;
    private Map<String, String> characteristics;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> city) {
        this.cities = city;
    }

    public Map<String, String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(Map<String, String> characteristics) {
        this.characteristics = characteristics;
    }
}
