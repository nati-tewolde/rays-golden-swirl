package com.pluralsight.raysgoldenswirl.items;

import com.pluralsight.raysgoldenswirl.toppings.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrozenYogurtTest {

    @Test
    public void addTopping_AddExtraQuantity_QuantityAccumulatesCorrectly() {
        // Arrange
        FrozenYogurt froyo = new FrozenYogurt("Test Froyo", "M");
        Topping nuts = new Nuts("Pecan");

        // Act
        froyo.addTopping(nuts, 1);
        froyo.addTopping(nuts, 2);

        int actualQuantity = froyo.getToppings().get(nuts);
        int expectedQuantity = 3;

        // Assert
        assertEquals(expectedQuantity, actualQuantity);
    }

    @Test
    public void calculatePrice_MediumSizeWithFruitNutsAndSauce_ReturnsCorrectTotal() {
        // Arrange
        FrozenYogurt froyo = new FrozenYogurt("Test Froyo", "M");

        Fruit fruit = new Fruit("Mango");
        Nuts nuts = new Nuts("Walnut");
        Sauce caramel = new Sauce("Caramel");

        froyo.addTopping(fruit, 1);
        froyo.addTopping(nuts, 1);
        froyo.addTopping(caramel, 1);

        double expectedTotal = 9.5;

        // Act
        double actualTotal = froyo.calculatePrice();

        // Assert
        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    @Test
    public void addTopping_ExceedsMaxToppings_ReturnsFalse() {
        // Arrange
        FrozenYogurt froyo = new FrozenYogurt("Test Froyo", "S");
        Topping candy = new Candy("Oreos");

        // Act
        boolean added1 = froyo.addTopping(candy, 1);
        boolean added2 = froyo.addTopping(candy, 2);
        boolean added3 = froyo.addTopping(candy, 1);

        // Assert
        assertTrue(added1);
        assertTrue(added2);
        assertFalse(added3);
    }
}