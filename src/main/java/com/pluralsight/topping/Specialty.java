package com.pluralsight.topping;

public class Specialty extends Topping {
    public Specialty(String type) {
        super(type);
    }

    @Override
    public double calculatePrice(String size) {
        return 0;
    }
}
