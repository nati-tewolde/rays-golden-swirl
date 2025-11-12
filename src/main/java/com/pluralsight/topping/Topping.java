package com.pluralsight.topping;

public abstract class Topping {
    private String type;

    public Topping(String type) {
        this.type = type;
    }

    public abstract double calculatePrice(String size);
}
