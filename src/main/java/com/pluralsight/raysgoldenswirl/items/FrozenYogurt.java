package com.pluralsight.raysgoldenswirl.items;

import com.pluralsight.raysgoldenswirl.toppings.Topping;

import java.util.ArrayList;
import java.util.List;

public class FrozenYogurt extends Item {
    private String size;
    private List<Topping> toppings;
    private boolean isRolled;

    public FrozenYogurt(String type, String size, boolean isRolled) {
        super(type);
        this.size = size;
        this.toppings = new ArrayList<>();
        this.isRolled = isRolled;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isRolled() {
        return isRolled;
    }

    public void addTopping(Topping topping) {
        // Limit extra topping maximum
        toppings.add(topping);
    }

    @Override
    public double calculatePrice() {
        double basePrice = 0;

        if (size.trim().equalsIgnoreCase("s")) {
            basePrice = 3.50;
        }
        if (size.trim().equalsIgnoreCase("m")) {
            basePrice = 6.00;
        }
        if (size.trim().equalsIgnoreCase("l")) {
            basePrice = 8.50;
        }

        /*double toppingsTotal = 0;
        for (Topping topping : toppings) {
            toppingsTotal += topping.calculatePrice(size);
        }*/

        double toppingsTotal = toppings.stream()
                .mapToDouble(toppings -> toppings.calculatePrice(size))
                .sum();

        return basePrice + toppingsTotal;
    }

    @Override
    public void displayDetails() {
        String specialOption;

        if (isRolled) {
            specialOption = "Rolled";
        } else {
            specialOption = "Regular";
        }

        System.out.printf("%s %s (%s) $%10.2f", specialOption, getType(), size.toUpperCase(), calculatePrice());

        for (Topping topping : toppings) {
            System.out.printf("%n - %s $%10.2f", topping.getType(), topping.calculatePrice(size));
        }
    }
}
