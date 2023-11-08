package com.joao.infrastructure.entity;

public class ProductUpdate {
    // ===== ATRIBUTOS =====
    private String description;
    private double price;
    private int amount;
    private int stock_min;
    private String dtupdate;

    // ===== MÉTODO CONTRUTOR =====
    public ProductUpdate(String description,
                         double price,
                         int amount,
                         int stock_min,
                         String dtupdate) {
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.stock_min = stock_min;
        this.dtupdate = dtupdate;
    }

    // ===== MÉTODO ACESSOR =====
    public String getDescription() {
        return description;
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

    public String getDtupdate() {
        return dtupdate;
    }
}
