package com.pluralsight.raysgoldenswirl.toppings;

public abstract class PremiumTopping extends Topping {
    public PremiumTopping(String type) {
        super(type);
    }

    public abstract double calculatePrice(String size);
}
