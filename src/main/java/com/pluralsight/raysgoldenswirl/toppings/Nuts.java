package com.pluralsight.raysgoldenswirl.toppings;

public class Nuts extends PremiumTopping {
    public Nuts(String type) {
        super(type);
    }

    @Override
    public double calculatePrice(String size) {
        double base = lookupBasePrice(size);
        double extra = lookupExtraPrice(size);
        double total = 0;

        if (getQuantity() == 1) {
            total = base;
        } else if (getQuantity() > 1) {
            total = base + ((getQuantity() - 1) * extra);
        }

        return total;
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
