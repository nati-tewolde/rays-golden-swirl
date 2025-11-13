package com.pluralsight.raysgoldenswirl.items;

import com.pluralsight.raysgoldenswirl.toppings.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FrozenYogurtTest {

    @Test
    public void addTopping_AddsMultipleToppings_AllAppearInList() {
        // Arrange
        List<Topping> toppings = new ArrayList<>();
        FrozenYogurt testFroyo = new FrozenYogurt("Test Froyo", "L", toppings, false);

        Topping fruit = new Fruit("Strawberry");
        Topping candy = new Candy("Sprinkles");
        int expectedCount = 2;

        // Act
        testFroyo.addTopping(fruit);
        testFroyo.addTopping(candy);

        // Assert
        int actualCount = toppings.size();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void calculatePrice_MediumSizeWithNutsFruitsAndSauce_AddsAllPrices() {
        // Arrange
        List<Topping> toppings = new ArrayList<>();
        Nuts nuts = new Nuts("Pecans");
        Fruit fruit = new Fruit("Pineapple");
        Sauce sauce = new Sauce("Caramel");

        toppings.add(nuts);
        toppings.add(fruit);
        toppings.add(sauce);

        FrozenYogurt testFroyo = new FrozenYogurt("Test Froyo", "M", toppings, false);
        double expectedPrice = 9.50;

        // Act
        double actualPrice = testFroyo.calculatePrice();

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
    }
}