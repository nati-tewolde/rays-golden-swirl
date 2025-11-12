package com.pluralsight.raysgoldenswirl.toppings;

public class Candy extends Topping {
    public Candy(String type) {
        super(type);
    }

    @Override
    public double calculatePrice(String size) {
        return 0;
    }
}
