package com.hanxuhui.netframework.protocol.bean;

import java.util.List;

/**
 * Created by hanxuhui on 2016/7/11.
 */
public class Locations {

    public List<Location> locationList;

    public Locations(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }
}
