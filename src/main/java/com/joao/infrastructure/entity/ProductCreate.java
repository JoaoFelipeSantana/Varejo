package com.joao.infrastructure.entity;

public class ProductCreate {
    // ===== ATRIBUTOS =====
    private String name_product;
    private String description_product;
    private double price;
    private int amount;
    private int stock_min;
    private String dtcreate;
    private String dtupdate;
    private boolean status_product;

    // ===== MÉTODO CONTRUTOR =====


    public ProductCreate(String name_product,
                         String description_product,
                         double price, int amount,
                         int stock_min,
                         String dtcreate,
                         String dtupdate,
                         boolean status_product) {

        this.name_product = name_product;
        this.description_product = description_product;
        this.price = price;
        this.amount = amount;
        this.stock_min = stock_min;
        this.dtcreate = dtcreate;
        this.dtupdate = dtupdate;
        this.status_product = status_product;
    }

    // ===== MÉTODOS ACESSORES =====
    public String getName_product() {
        return name_product;
    }

    public String getDescription_product() {
        return description_product;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getStock_min() {
        return stock_min;
    }

    public String getDtcreate() {
        return dtcreate;
    }

    public String getDtupdate() {
        return dtupdate;
    }

    public boolean isStatus_product() {
        return status_product;
    }
}
