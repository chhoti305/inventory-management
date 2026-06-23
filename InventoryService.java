package com.inventory.service;

import com.inventory.dao.ItemDAO;
import com.inventory.dao.ItemDAOImpl;
import com.inventory.model.Item;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service layer for Inventory business logic.
 * Isolates core operations from DAO and presentation layers.
 */
public class InventoryService {

    private final ItemDAO itemDAO;
    private static final int LOW_STOCK_THRESHOLD = 10;

    public InventoryService() {
        this.itemDAO = new ItemDAOImpl();
    }

    public void addItem(Item item) {
        validateItem(item);
        itemDAO.addItem(item);
    }

    public Item getItem(int id) {
        Item item = itemDAO.getItemById(id);
        if (item == null) throw new RuntimeException("Item not found with ID: " + id);
        return item;
    }

    public List<Item> getAllItems() {
        return itemDAO.getAllItems();
    }

    public void updateItem(Item item) {
        validateItem(item);
        itemDAO.updateItem(item);
    }

    public void deleteItem(int id) {
        itemDAO.deleteItem(id);
    }

    public List<Item> getLowStockAlerts() {
        return itemDAO.getLowStockItems(LOW_STOCK_THRESHOLD);
    }

    /**
     * Algorithmic optimization: Uses HashMap to aggregate total stock value by category.
     * O(n) time complexity for processing large transactional datasets.
     */
    public Map<String, Double> getCategoryWiseValue() {
        List<Item> items = itemDAO.getAllItems();
        Map<String, Double> categoryValue = new HashMap<>();

        for (Item item : items) {
            categoryValue.merge(item.getCategory(),
                item.getQuantity() * item.getPrice(), Double::sum);
        }
        return categoryValue;
    }

    /**
     * Sorts items by value (price * quantity) descending using Collections.
     */
    public List<Item> getItemsSortedByValue() {
        List<Item> items = new ArrayList<>(itemDAO.getAllItems());
        items.sort((a, b) -> Double.compare(
            b.getPrice() * b.getQuantity(),
            a.getPrice() * a.getQuantity()
        ));
        return items;
    }

    /**
     * Returns summary statistics for dashboard monitoring.
     */
    public Map<String, Object> getSummaryStats() {
        List<Item> items = itemDAO.getAllItems();
        Map<String, Object> stats = new LinkedHashMap<>();

        stats.put("totalItems", items.size());
        stats.put("totalStockValue", items.stream()
            .mapToDouble(i -> i.getPrice() * i.getQuantity()).sum());
        stats.put("lowStockCount", getLowStockAlerts().size());
        stats.put("categories", items.stream()
            .map(Item::getCategory).collect(Collectors.toSet()).size());

        return stats;
    }

    private void validateItem(Item item) {
        if (item.getName() == null || item.getName().isEmpty())
            throw new IllegalArgumentException("Item name cannot be empty");
        if (item.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");
        if (item.getPrice() < 0)
            throw new IllegalArgumentException("Price cannot be negative");
    }
}
