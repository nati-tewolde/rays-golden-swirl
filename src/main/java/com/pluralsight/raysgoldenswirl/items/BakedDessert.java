package com.pluralsight.raysgoldenswirl.items;

public class BakedDessert extends Item {
    public BakedDessert(String type) {
        super(type);
    }

    @Override
    public double calculatePrice() {
        return 1.50;
    }

    @Override
    public void displayDetails() {
        System.out.printf("%s %10s", getType(), String.format("$%.2f", calculatePrice()));
    }

    @Override
    public String getReceiptDetails() {
        return String.format("%s %10s", getType(), String.format("$%.2f", calculatePrice()));
    }
}
