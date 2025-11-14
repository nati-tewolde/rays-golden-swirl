package com.pluralsight.raysgoldenswirl.toppings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    public void calculatePrice_LargeSize_ReturnsCorrectBasePrice() {
        // Arrange
        Fruit kiwi = new Fruit("Kiwi");
        String size = "L";
        double expectedPrice = 3.00;

        // Act
        double actual = kiwi.getPrice(size);

        // Assert
        assertEquals(expectedPrice, actual, 0.001);
    }

    @Test
    public void getExtraPrice_MediumSize_ReturnsCorrectExtraPrice() {
        // Arrange
        Fruit mango = new Fruit("Mango");
        String size = "M";
        double expectedExtra = 1.00;

        // Act
        double actualExtra = mango.getExtraPrice(size);

        // Assert
        assertEquals(expectedExtra, actualExtra, 0.001);
    }
}