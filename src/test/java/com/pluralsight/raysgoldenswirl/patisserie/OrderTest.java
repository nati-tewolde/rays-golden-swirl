package com.pluralsight.raysgoldenswirl.patisserie;

import com.pluralsight.raysgoldenswirl.items.FrozenYogurt;
import com.pluralsight.raysgoldenswirl.items.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    public void addItem_AddsItemToOrder_ListSizeIncreases() {
        // Arrange
        Order order = new Order("Raymond");
        Item froyo = new FrozenYogurt("Froyo", "M");

        int expectedSize = 1;

        // Act
        order.addItem(froyo);
        int actualSize = order.getItems().size();

        // Assert
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void calculateTotal_WithMultipleItems_ReturnsSumOfPrices() {
        // Arrange
        Order order = new Order("Raymond");

        FrozenYogurt froyo1 = new FrozenYogurt("Froyo", "S");
        FrozenYogurt froyo2 = new FrozenYogurt("Froyo", "M");

        order.addItem(froyo1);
        order.addItem(froyo2);

        double expectedTotal = 3.50 + 6.00;

        // Act
        double actualTotal = order.calculateTotal();

        // Assert
        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    @Test
    public void getOrderNumber_ReturnsFourDigitString() {
        // Arrange
        Order order = new Order("Raymond");

        // Act
        String number = order.getOrderNumber();

        // Assert
        assertEquals(4, number.length());
        assertTrue(number.matches("\\d{4}"));
    }
}