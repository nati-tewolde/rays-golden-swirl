package com.pluralsight.raysgoldenswirl.toppings;

public abstract class Topping {
    private String type;
    private int quantity;

    public Topping(String type) {
        this.type = type;
        this.quantity = 1;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity() {
        quantity += 1;
    }

    public abstract double calculatePrice(String size);
}
