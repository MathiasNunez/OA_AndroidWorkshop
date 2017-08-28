package com.oa.android_workshop.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mnunez on 8/21/17.
 */

public class Company implements Serializable{

    private static final long serialVersionUID = -7487980506293564203L;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("direction")
    @Expose
    private String address;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("logo")
    @Expose
    private String logoUrl;

    @SerializedName("url")
    @Expose
    private String webSiteUrl;

    @SerializedName("lat")
    @Expose
    private Double latitude;

    @SerializedName("lng")
    @Expose
    private Double longitude;

    public Company() {
    }

    public Company(String name, String address, String phone, String logoUrl, String webSiteUrl, Double latitude, Double longitude) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.logoUrl = logoUrl;
        this.webSiteUrl = webSiteUrl;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public void setWebSiteUrl(String webSiteUrl) {
        this.webSiteUrl = webSiteUrl;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
