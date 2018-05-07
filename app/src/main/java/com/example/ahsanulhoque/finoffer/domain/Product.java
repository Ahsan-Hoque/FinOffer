package com.example.ahsanulhoque.finoffer.domain;

import java.io.Serializable;


public class Product implements Serializable{

    private String id;
    private String merchantId;
    private String name;
    private double price;
    private String description;
    private double discountRate;
    private double regularPrice;
    private String items;
    private String location;
    private String productTypeId;

    public Product() {
    }

    public Product(String id, String merchantId, String name, double price, String description, double discountRate, double regularPrice, String items, String location, String productTypeId) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discountRate = discountRate;
        this.regularPrice = regularPrice;
        this.items = items;
        this.location = location;
        this.productTypeId = productTypeId;
    }

    public Product(String merchantId, String name, double price, String description, double discountRate, double regularPrice, String items, String location, String productTypeId) {
        this.merchantId = merchantId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discountRate = discountRate;
        this.regularPrice = regularPrice;
        this.items = items;
        this.location = location;
        this.productTypeId = productTypeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", merchantId=" + merchantId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", discountRate=" + discountRate +
                ", regularPrice=" + regularPrice +
                ", items='" + items + '\'' +
                ", location='" + location + '\'' +
                ", productTypeId=" + productTypeId +
                '}';
    }

}
