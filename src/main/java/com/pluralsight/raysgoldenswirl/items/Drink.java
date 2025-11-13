package com.pluralsight.raysgoldenswirl.items;

public class Drink extends Item {
    private String size;

    public Drink(String type, String size) {
        super(type);
        this.size = size;
    }
    /**
     * Calculates the price of the item depending on its size
     *
     * @return Base price
     */
    @Override
    public double calculatePrice() {
        double basePrice = 0;

        if (size.trim().equalsIgnoreCase("s")) {
            basePrice = 2.00;
        }
        if (size.trim().equalsIgnoreCase("m")) {
            basePrice = 2.50;
        }
        if (size.trim().equalsIgnoreCase("l")) {
            basePrice = 3.00;
        }

        return basePrice;
    }

    @Override
    public void displayDetails() {
        System.out.printf("%s (%s) $%10.2f", getType(), size.toUpperCase(), calculatePrice());
    }

    @Override
    public String getReceiptDetails() {
        return String.format("%s (%s) $%10.2f", getType(), size.toUpperCase(), calculatePrice());
    }
}
