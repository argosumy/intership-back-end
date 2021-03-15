package com.spd.baraholka.notification.model;


import java.util.List;
//New Comments or new Advertisement
public class AdvertisementNotification extends Notification{
    private String nameAd;
    private String sendToOwner;
    private long price;
    private String currency;
    private String mainImage;
    private List<String> images;
    private String location;
    private String linkAd;
    private String nameWriter;



    public AdvertisementNotification() {

    }

    public String getNameAd() {
        return nameAd;
    }

    public void setNameAd(String nameAd) {
        this.nameAd = nameAd;
    }

    public String getSendToOwner() {
        return sendToOwner;
    }

    public void setSendToOwner(String sendToOwner) {
        this.sendToOwner = sendToOwner;
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



    public String getLinkAd() {
        return linkAd;
    }

    public void setLinkAd(String linkAd) {
        this.linkAd = linkAd;
    }

    public String getNameWriter() {
        return nameWriter;
    }

    public void setNameWriter(String nameWriter) {
        this.nameWriter = nameWriter;
    }
}
