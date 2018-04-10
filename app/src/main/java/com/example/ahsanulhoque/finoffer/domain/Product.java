package com.example.ahsanulhoque.finoffer.domain;


public class Product {

    private String id;
    private String merchantId;
    private String name;
    private double price;
    private String description;
    private double discountRate;
    private String items;
    private String productTypeId;

    public Product() {
    }

    public Product(String id, String merchantId, String name, double price, String description, double discountRate, String items, String productTypeId) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discountRate = discountRate;
        this.items = items;
        this.productTypeId = productTypeId;
    }

    public Product(String merchantId, String name, double price, String description, double discountRate, String items, String productTypeId) {
        this.merchantId = merchantId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discountRate = discountRate;
        this.items = items;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
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
                ", items='" + items + '\'' +
                ", productTypeId=" + productTypeId +
                '}';
    }

}
