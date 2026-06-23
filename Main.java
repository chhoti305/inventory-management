package com.inventory;

import com.inventory.model.Item;
import com.inventory.service.InventoryService;

import java.util.List;
import java.util.Map;

/**
 * Main entry point for the Inventory Management System.
 * Demonstrates CRUD, algorithmic sorting, and stats dashboard.
 */
public class Main {

    public static void main(String[] args) {
        InventoryService service = new InventoryService();

        // --- Add sample items ---
        service.addItem(new Item(0, "Laptop", "Electronics", 50, 45000.00, "Dell India"));
        service.addItem(new Item(0, "Desk Chair", "Furniture", 8, 7500.00, "Featherlite"));
        service.addItem(new Item(0, "Notebook", "Stationery", 200, 45.00, "Classmate"));
        service.addItem(new Item(0, "Monitor", "Electronics", 5, 15000.00, "LG"));

        // --- Display all items ---
        System.out.println("\n===== ALL INVENTORY ITEMS =====");
        List<Item> items = service.getAllItems();
        items.forEach(System.out::println);

        // --- Low stock alerts ---
        System.out.println("\n===== LOW STOCK ALERTS =====");
        service.getLowStockAlerts().forEach(i ->
            System.out.println("LOW STOCK: " + i.getName() + " | Qty: " + i.getQuantity()));

        // --- Category-wise value ---
        System.out.println("\n===== CATEGORY-WISE STOCK VALUE =====");
        Map<String, Double> categoryValue = service.getCategoryWiseValue();
        categoryValue.forEach((cat, val) ->
            System.out.printf("%-15s : ₹%.2f%n", cat, val));

        // --- Items sorted by total value ---
        System.out.println("\n===== ITEMS SORTED BY VALUE (DESC) =====");
        service.getItemsSortedByValue().forEach(i ->
            System.out.printf("%-15s : ₹%.2f%n", i.getName(), i.getPrice() * i.getQuantity()));

        // --- Summary dashboard ---
        System.out.println("\n===== SUMMARY DASHBOARD =====");
        service.getSummaryStats().forEach((k, v) ->
            System.out.println(k + " : " + v));
    }
}
