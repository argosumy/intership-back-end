package com.spd.baraholka.advertisements.persistance;

import com.spd.baraholka.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class Advertisement {

    private User owner;
    private String title;
    private String description;
    private String category;
    private double price;
    private CurrencyType currency;
    private boolean discountAvailability;
    private String city;
    private AdvertisementStatus status;
    private LocalDateTime creationDate;
    private LocalDateTime publicationDate;
    private LocalDateTime statusChangeDate;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public boolean isDiscountAvailability() {
        return discountAvailability;
    }

    public void setDiscountAvailability(boolean discountAvailability) {
        this.discountAvailability = discountAvailability;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public AdvertisementStatus getStatus() {
        return status;
    }

    public void setStatus(AdvertisementStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    public void setStatusChangeDate(LocalDateTime statusChangeDate) {
        this.statusChangeDate = statusChangeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Double.compare(that.price, price) == 0 && discountAvailability == that.discountAvailability && owner.equals(that.owner) && title.equals(that.title) && description.equals(that.description) && category.equals(that.category) && currency == that.currency && city.equals(that.city) && status == that.status && creationDate.equals(that.creationDate) && publicationDate.equals(that.publicationDate) && statusChangeDate.equals(that.statusChangeDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, title, description, category, price, currency, discountAvailability, city, status, creationDate, publicationDate, statusChangeDate);
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "owner=" + owner +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", discountAvailability=" + discountAvailability +
                ", city='" + city + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", publicationDate=" + publicationDate +
                ", statusChangeDate=" + statusChangeDate +
                '}';
    }
}