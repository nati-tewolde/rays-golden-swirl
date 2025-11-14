package com.pluralsight.raysgoldenswirl.toppings;

public class Sauce extends Topping {
    public Sauce(String type) {
        super(type);
    }

    @Override
    public double getPrice(String size) {
        return 0;
    }
}
