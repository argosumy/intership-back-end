package com.spduniversity.notifications.model;

import java.util.List;


public class Advertisemenets extends Notification{
    private int id;
    private String nameAd;
    private long price;
    private String currency;
    private String mainImage;
    private List<String> images;
    private String location;


    public Advertisemenets() {

    }

    @Override
    public String getSubject() {
        return super.getSubject();
    }

    @Override
    public void setSubject(String subject) {
        super.setSubject(subject);
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(String description) {
        super.setDescription(description);
    }

    @Override
    public User getSendTo() {
        return super.getSendTo();
    }

    @Override
    public void setSendTo(User sendTo) {
        super.setSendTo(sendTo);
    }

    @Override
    public User getSendFrom() {
        return super.getSendFrom();
    }

    @Override
    public void setSendFrom(User sendFrom) {
        super.setSendFrom(sendFrom);
    }

    @Override
    public String getEvent() {
        return super.getEvent();
    }

    @Override
    public void setEvent(String event) {
        super.setEvent(event);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
}
