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
        System.out.printf("%s $%10.2f", getType(), calculatePrice());
    }
}
