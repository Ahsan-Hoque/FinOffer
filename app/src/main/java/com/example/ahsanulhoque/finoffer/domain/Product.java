package com.example.ahsanulhoque.finoffer.domain;


public class Product {

    private long id;
    private long merchantId;
    private String name;
    private double price;
    private String description;
    private double discountRate;
    private String items;
    private long productTypeId;

    public Product() {
    }

    public Product(long id, long merchantId, String name, double price, String description, double discountRate, String items, long productTypeId) {
        this.id = id;
        this.merchantId = merchantId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.discountRate = discountRate;
        this.items = items;
        this.productTypeId = productTypeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
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

    public long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
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
