package com.pluralsight.raysgoldenswirl.items;

public abstract class Item {
    private String type;

    public Item(String type) {
        this.type = type;
    }

    public abstract double calculatePrice();

    public abstract void displayDetails();
}
