package com.model;

import java.time.LocalDate;

public class Medicine {

    private int id;
    private String name;
    private String supplier;
    private int quantity;
    private LocalDate expiryDate;

    public Medicine() {
    }

    public Medicine(int id, String name, String supplier, int quantity, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public Medicine(String name, String supplier, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.supplier = supplier;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
