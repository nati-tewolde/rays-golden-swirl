package com.pluralsight.raysgoldenswirl.patisserie;

import com.pluralsight.raysgoldenswirl.items.Item;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReceiptFileManager {


    public String getReceiptFileName(Order order) {
        return order.getOrderDateForFile() + ".txt";
    }

    /**
     * Writes the order details to a newly created file path
     *
     * @param order The current order being written to file
     */
    public void saveReceipt(Order order) {
        String directory = "receipts/";
        String fileName = getReceiptFileName(order);
        File receipt = new File(directory, fileName);

        File parentDirectory = receipt.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(receipt))) {
            writer.write("ORDER #" + order.getOrderNumber());
            writer.write("Customer: " + order.getCustomerName());
            writer.write("Date: " + order.getOrderDateForDisplay());

            int itemNumber = 1;
            for (Item item : order.getItems()) {
                writer.write(itemNumber + ". ");
                writer.write(item.getReceiptDetails());
                itemNumber += 1;
            }

            writer.write("Total: " + order.calculateTotal());

            System.out.println("Receipt saved successfully within " + directory + fileName);

        } catch (IOException ex) {
            System.out.println("Error saving receipt: " + ex.getMessage());
        }
    }
}
