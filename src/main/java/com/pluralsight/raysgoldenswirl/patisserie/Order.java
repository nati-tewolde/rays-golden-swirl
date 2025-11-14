package com.pluralsight.raysgoldenswirl.patisserie;

import com.pluralsight.raysgoldenswirl.items.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    private String customerName;
    private List<Item> items;
    private LocalDateTime orderDate;

    public Order(String customerName) {
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getOrderNumber() {
        Random random = new Random();
        int orderNumber = random.nextInt(1000);
        return String.format("%04d", orderNumber);
    }

    public List<Item> getItems() {
        return items;
    }

    public String getOrderDateForDisplay() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a");
        return orderDate.format(formatter);
    }

    public String getOrderDateForFile() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-hhmmss");
        return orderDate.format(formatter);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double calculateTotal() {
        return items.stream()
                .mapToDouble(Item::calculatePrice)
                .sum();
    }

    public void displayOrder() {
        System.out.println("ORDER #" + getOrderNumber());
        System.out.println("Customer: " + customerName);
        System.out.println("Date: " + getOrderDateForDisplay());

        int itemNumber = 1;
        for (Item item : items) {
            System.out.println(itemNumber + ". ");
            item.displayDetails();
            itemNumber += 1;
        }

        System.out.printf("Total: $%10.2f", calculateTotal());
    }
}
