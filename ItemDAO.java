package com.inventory.dao;

import com.inventory.model.Item;
import java.util.List;

/**
 * DAO Interface for Item entity.
 * Isolates business logic from database access scripts (DAO Design Pattern).
 */
public interface ItemDAO {

    void addItem(Item item);

    Item getItemById(int id);

    List<Item> getAllItems();

    void updateItem(Item item);

    void deleteItem(int id);

    List<Item> getLowStockItems(int threshold);

    List<Item> getItemsByCategory(String category);
}
