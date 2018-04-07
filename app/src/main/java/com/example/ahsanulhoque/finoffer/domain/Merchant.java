package com.example.ahsanulhoque.finoffer.domain;


public class Merchant {

    private long id;
    private String brandName;
    private double latitude;
    private double longitude;
    private String webLink;
    private String hotNumber;
    private String email;

    public Merchant() {
    }

    public Merchant(long id, String brandName, double latitude, double longitude, String webLink, String hotNumber, String email) {
        this.id = id;
        this.brandName = brandName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.webLink = webLink;
        this.hotNumber = hotNumber;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(String hotNumber) {
        this.hotNumber = hotNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", webLink='" + webLink + '\'' +
                ", hotNumber='" + hotNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
