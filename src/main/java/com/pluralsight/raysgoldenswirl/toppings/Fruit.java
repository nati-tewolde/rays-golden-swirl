package com.pluralsight.raysgoldenswirl.toppings;

public class Fruit extends PremiumTopping {
    public Fruit(String type) {
        super(type);
    }

    /**
     * Calculates the total price of the topping including extra toppings
     *
     * @param size Size of the overarching item
     * @return Sum of the base price and any extra toppings
     */
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

    /**
     * Stores the fixed base prices of the topping depending on size
     *
     * @param size Size of the overarching item
     * @return Base price
     */
    public double lookupBasePrice(String size) {
        if (size.trim().equalsIgnoreCase("l")) {
            return 3.00;
        } else if (size.trim().equalsIgnoreCase("m")) {
            return 2.00;
        } else {
            return 1.00;
        }
    }

    /**
     * Stores the fixed extra prices of the topping depending on size
     *
     * @param size Size of the overarching item
     * @return Extra price
     */
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
