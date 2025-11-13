package com.pluralsight.raysgoldenswirl.toppings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    public void calculatePrice_SmallSizeWithOneTopping_ReturnBasePrice() {
        // Arrange
        Fruit strawberry = new Fruit("strawberry");
        String size = "S";
        double expectedPrice = 1.00;

        // Act
        double actualPrice = strawberry.calculatePrice(size);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001); // maximum difference allowed for assertion to pass
    }

    @Test
    public void calculatePrice_MediumSizeWithTwoServings_ReturnsBasePlusExtra() {
        // Arrange
        Fruit kiwi = new Fruit("kiwi");
        kiwi.increaseQuantity();
        String size = "M";
        double expectedPrice = 3.00;

        // Act
        double actualPrice = kiwi.calculatePrice(size);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.001);
    }

    @Test
    public void increaseQuantity_CalledOnce_QuantityIncrementedByOne() {
        // Arrange
        Fruit pineapple = new Fruit("pineapple");
        int expectedQuantity = 2;

        // Act
        pineapple.increaseQuantity();

        // Assert
        int actualQuantity = pineapple.getQuantity();
        assertEquals(expectedQuantity, actualQuantity);
    }

}