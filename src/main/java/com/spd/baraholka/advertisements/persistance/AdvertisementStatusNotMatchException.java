package com.spd.baraholka.advertisements.persistance;

public class AdvertisementStatusNotMatchException extends RuntimeException {

    public AdvertisementStatusNotMatchException(String supposedStatus) {
        super("Incorrect status for advertisement:" + supposedStatus);
    }
}
