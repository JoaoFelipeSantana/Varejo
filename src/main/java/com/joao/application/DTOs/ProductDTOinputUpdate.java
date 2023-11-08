package com.joao.application.DTOs;

public class ProductDTOinputUpdate {
    // ===== ATRIBUTOS =====
    private String description;
    private double price;
    private int amount;
    private int stock_min;

    // ===== MÉTODO CONTRUTOR =====
    public ProductDTOinputUpdate(String description,
                                 double price,
                                 int amount,
                                 int stock_min) {
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.stock_min = stock_min;
    }

    // ===== MÉTODOS ACESSORES =====
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
}
