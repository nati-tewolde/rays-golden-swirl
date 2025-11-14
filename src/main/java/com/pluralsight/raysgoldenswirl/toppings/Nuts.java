package com.pluralsight.raysgoldenswirl.toppings;

public class Nuts extends PremiumTopping {
    public Nuts(String type) {
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
            return 2.25;
        } else if (size.trim().equalsIgnoreCase("m")) {
            return 1.50;
        } else {
            return 0.75;
        }
    }

    public double lookupExtraPrice(String size) {
        if (size.trim().equalsIgnoreCase("l")) {
            return 0.90;
        } else if (size.trim().equalsIgnoreCase("m")) {
            return 0.60;
        } else {
            return 0.30;
        }
    }
}
