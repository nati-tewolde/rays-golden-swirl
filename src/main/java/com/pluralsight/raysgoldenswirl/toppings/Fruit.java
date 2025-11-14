package com.pluralsight.raysgoldenswirl.toppings;

public class Fruit extends PremiumTopping {
    public Fruit(String type) {
        super(type);
    }

    @Override
    public double getPrice(String size) {
        return lookupBasePrice(size);
    }

    @Override
    public double getExtraPrice(String size) {
        return lookupExtraPrice(size);
    }

    public double lookupBasePrice(String size) {
        if (size.trim().equalsIgnoreCase("l")) {
            return 3.00;
        } else if (size.trim().equalsIgnoreCase("m")) {
            return 2.00;
        } else {
            return 1.00;
        }
    }

    public double lookupExtraPrice(String size) {
        if (size.trim().equalsIgnoreCase("l")) {
            return 1.50;
        } else if (size.trim().equalsIgnoreCase("m")) {
            return 1.00;
        } else {
            return 0.50;
        }
    }
}
