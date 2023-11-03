package com.joao.application.DTOs;

public class ProductDTOinput {
    private String name_product;
    private String desription_product;
    private float price;
    private int amount;
    private int stock_min;
    private boolean status_product;


    // ===== MÉTODO CONTRUTOR =====
    public ProductDTOinput(String name_product,
                           String desription_product,
                           float price,
                           int amount,
                           int stock_min) {
        this.name_product = name_product;
        this.desription_product = desription_product;
        this.price = price;
        this.amount = amount;
        this.stock_min = stock_min;
    }

    // ===== MÉTODOS ACESSORES =====


    public String getName_product() {
        return name_product;
    }

    public String getDesription_product() {
        return desription_product;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public int getStock_min() {
        return stock_min;
    }

}
