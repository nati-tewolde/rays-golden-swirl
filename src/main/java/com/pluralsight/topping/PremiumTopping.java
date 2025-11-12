package com.pluralsight.topping;

public abstract class PremiumTopping extends Topping {
    public PremiumTopping(String type) {
        super(type);
    }

    public abstract double calculatePrice(String size);
}
