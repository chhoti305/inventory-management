package com.inventory.model;

/**
 * Represents an inventory item entity.
 * Maps directly to the 'items' table in MySQL.
 */
public class Item {

    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;
    private String supplier;

    public Item() {}

    public Item(int id, String name, String category, int quantity, double price, String supplier) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.supplier = supplier;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    @Override
    public String toString() {
        return "Item{id=" + id + ", name='" + name + "', category='" + category +
               "', quantity=" + quantity + ", price=" + price + ", supplier='" + supplier + "'}";
    }
}
