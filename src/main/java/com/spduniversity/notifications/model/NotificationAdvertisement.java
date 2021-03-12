package com.spduniversity.notifications.model;

import java.util.List;


public class NotificationAdvertisement extends Notification{
    private int id;
    private String nameAd;
    private long price;
    private String currency;
    private String mainImage;
    private List<String> images;
    private String location;
    private String linkAd;


    public NotificationAdvertisement() {

    }

    public String getNameAd() {
        return nameAd;
    }

    public void setNameAd(String nameAd) {
        this.nameAd = nameAd;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLinkAd() {
        return linkAd;
    }

    public void setLinkAd(String linkAd) {
        this.linkAd = linkAd;
    }
}
