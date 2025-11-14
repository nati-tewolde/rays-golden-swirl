package com.pluralsight.raysgoldenswirl.toppings;

public class Specialty extends Topping {
    public Specialty(String type) {
        super(type);
    }

    @Override
    public double getPrice(String size) {
        return 0;
    }
}
