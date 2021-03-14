package com.spd.baraholka.advertisements.persistance;

import java.time.LocalDateTime;
import java.util.Objects;

public class Advertisement {

    private int advertisementId;
    private int ownerId;
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

    public Advertisement() {
    }

    public Advertisement(int advertisementId, int ownerId, String title, String description, String category, double price,
                         CurrencyType currency, boolean discountAvailability, String city, AdvertisementStatus status, LocalDateTime creationDate,
                         LocalDateTime publicationDate, LocalDateTime statusChangeDate) {
        this.advertisementId = advertisementId;
        this.ownerId = ownerId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.currency = currency;
        this.discountAvailability = discountAvailability;
        this.city = city;
        this.status = status;
        this.creationDate = creationDate;
        this.publicationDate = publicationDate;
        this.statusChangeDate = statusChangeDate;
    }

    public int getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(int advertisementId) {
        this.advertisementId = advertisementId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Advertisement)) {
            return false;
        }
        Advertisement that = (Advertisement) o;
        return getAdvertisementId() == that.getAdvertisementId() && getOwnerId() == that.getOwnerId() && Double.compare(that.getPrice(), getPrice()) == 0 &&
                isDiscountAvailability() == that.isDiscountAvailability() && Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getCategory(), that.getCategory()) &&
                getCurrency() == that.getCurrency() && Objects.equals(getCity(), that.getCity()) && getStatus() == that.getStatus() &&
                Objects.equals(getCreationDate(), that.getCreationDate()) && Objects.equals(getPublicationDate(), that.getPublicationDate()) &&
                Objects.equals(getStatusChangeDate(), that.getStatusChangeDate());
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(getAdvertisementId(), getOwnerId(), getTitle(), getDescription(), getCategory(), getPrice(), getCurrency(), isDiscountAvailability(), getCity(),
                        getStatus(), getCreationDate(), getPublicationDate(), getStatusChangeDate());
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "advertisementId=" + advertisementId +
                ", ownerId=" + ownerId +
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