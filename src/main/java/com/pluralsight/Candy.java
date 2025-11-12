package com.pluralsight;

public class Candy extends Topping {

    public Candy(String type) {
        super(type);
    }

    @Override
    public double calculatePrice(String size) {
        return 0;
    }
}
