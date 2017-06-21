package com.hanxuhui.netframework.protocol.bean;

/**
 * Created by hanxuhui on 2016/7/11.
 */
public class Location {

    //"address":"北京市西城区武定侯街靠近中国银行(北京金融中心支行)","altitude":"0.0","latitude":"39.918104","longtitude":"116.358343","priority":0,"scene":1,"timestamp":"2016-07-11 15:47:00"}

    public double longtitude;
    public double latitude;
    public double altitude;
    public String address;

    public String priority;
    public String scene;
    public String timestamp;

    public Location(double longtitude, double latitude, double altitude, String address, String priority, String scene, String timestamp) {
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.address = address;
        this.priority = priority;
        this.scene = scene;
        this.timestamp = timestamp;
    }

}
