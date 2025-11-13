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
        FrozenYogurt testFroyo = new FrozenYogurt("Test Froyo", "L", false);
        Topping fruit = new Fruit("Strawberry");
        Topping candy = new Candy("Sprinkles");
        int expectedCount = 2;

        // Act
        testFroyo.addTopping(fruit);
        testFroyo.addTopping(candy);

        // Assert
        int actualCount = testFroyo.getToppings().size();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void calculatePrice_MediumSizeWithNutsFruitsAndSauce_AddsAllPrices() {
        // Arrange
        FrozenYogurt testFroyo = new FrozenYogurt("Test Froyo", "M", false);
        Nuts nuts = new Nuts("Pecans");
        Fruit fruit = new Fruit("Pineapple");
        Sauce sauce = new Sauce("Caramel");

        testFroyo.addTopping(nuts);
        testFroyo.addTopping(fruit);
        testFroyo.addTopping(sauce);
        double expectedPrice = 9.50;

        // Act
        double actualPrice = testFroyo.calculatePrice();

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
    }
}