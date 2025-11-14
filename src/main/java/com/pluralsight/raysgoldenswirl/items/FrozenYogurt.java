package com.pluralsight.raysgoldenswirl.items;

import com.pluralsight.raysgoldenswirl.toppings.PremiumTopping;
import com.pluralsight.raysgoldenswirl.toppings.Topping;

import java.util.HashMap;
import java.util.Map;

public class FrozenYogurt extends Item {
    private String size;
    private Map<Topping, Integer> toppings;
    private boolean isRolled;
    private int toppingsCount;
    private int maxToppings;

    public FrozenYogurt(String type, String size) {
        super(type);
        this.size = size;
        this.toppings = new HashMap<>();
        this.toppingsCount = 0;

        if (size.trim().equalsIgnoreCase("l")) {
            this.maxToppings = 7;
        } else if (size.trim().equalsIgnoreCase("m")) {
            this.maxToppings = 5;
        } else {
            this.maxToppings = 3;
        }

        this.isRolled = false;
    }

    public Map<Topping, Integer> getToppings() {
        return toppings;
    }

    public void setRolled(boolean rolled) {
        isRolled = rolled;
    }

    public int getToppingsCount() {
        return toppingsCount;
    }

    public void setToppingsCount(int toppingsCount) {
        this.toppingsCount = toppingsCount;
    }

    public int getMaxToppings() {
        return maxToppings;
    }

    public void setMaxToppings(int maxToppings) {
        this.maxToppings = maxToppings;
    }

    public boolean addTopping(Topping topping, int quantity) {
        if ((toppingsCount + quantity) > maxToppings) {
            return false;
        }

        int currentQuantity = toppings.getOrDefault(topping, 0);
        toppings.put(topping, currentQuantity + quantity);
        toppingsCount += quantity;

        return true;
    }

    @Override
    public double calculatePrice() {
        double total = lookUpFrozenYogurtBasePrice(size);

        for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
            total += calculateToppingTotal(entry.getKey(), entry.getValue());
        }

        return total;
    }
    
    public double lookUpFrozenYogurtBasePrice(String size) {
        if (size.trim().equalsIgnoreCase("l")) {
            return 8.50;
        } else if (size.trim().equalsIgnoreCase("m")) {
            return 6.00;
        } else {
            return 3.50;
        }
    }

    public double calculateToppingTotal(Topping topping, int quantity) {
        double unitPrice = topping.getPrice(size);
        double extraPrice;

        if (topping instanceof PremiumTopping) {
            extraPrice = ((PremiumTopping) topping).getExtraPrice(size);
        } else {
            extraPrice = unitPrice;
        }

        if (quantity == 1) {
            return unitPrice;
        }

        return unitPrice + (quantity - 1) * extraPrice;
    }

    @Override
    public void displayDetails() {
        String specialOption = isRolled ? "Rolled" : "Regular";

        System.out.printf("%s %s (%s) $%10.2f", specialOption, getType(),
                size.toUpperCase(), calculatePrice());

        for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
            double toppingCost = calculateToppingTotal(entry.getKey(), entry.getValue());

            System.out.printf("%n - %s (x%d) $%10.2f", entry.getKey().getType(),
                    entry.getValue(), toppingCost);
        }
    }

    @Override
    public String getReceiptDetails() {
        StringBuilder details = new StringBuilder();
        String specialOption = isRolled ? "Rolled" : "Regular";
        details.append(String.format("%s %s (%s) %10s\n",
                specialOption, getType(), size.toUpperCase(), String.format("$%.2f", calculatePrice())));

        for (Map.Entry<Topping, Integer> entry : toppings.entrySet()) {
            double toppingCost = calculateToppingTotal(entry.getKey(), entry.getValue());

            details.append(String.format("- 5%s (x%d) %10s\n",
                    entry.getKey().getType(), entry.getValue(), String.format("$%.2f", toppingCost)));
        }
        return details.toString();
    }
}
