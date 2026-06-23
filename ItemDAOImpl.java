package com.inventory.dao;

import com.inventory.model.Item;
import com.inventory.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL implementation of ItemDAO.
 * Handles all CRUD operations and query optimization with indexed columns.
 */
public class ItemDAOImpl implements ItemDAO {

    @Override
    public void addItem(Item item) {
        String sql = "INSERT INTO items (name, category, quantity, price, supplier) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getCategory());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.setString(5, item.getSupplier());
            ps.executeUpdate();
            System.out.println("Item added: " + item.getName());

        } catch (SQLException e) {
            throw new RuntimeException("Error adding item: " + e.getMessage(), e);
        }
    }

    @Override
    public Item getItemById(int id) {
        String sql = "SELECT * FROM items WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching item: " + e.getMessage(), e);
        }
        return null;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY category, name";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) items.add(mapRow(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching items: " + e.getMessage(), e);
        }
        return items;
    }

    @Override
    public void updateItem(Item item) {
        String sql = "UPDATE items SET name=?, category=?, quantity=?, price=?, supplier=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, item.getName());
            ps.setString(2, item.getCategory());
            ps.setInt(3, item.getQuantity());
            ps.setDouble(4, item.getPrice());
            ps.setString(5, item.getSupplier());
            ps.setInt(6, item.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating item: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteItem(int id) {
        String sql = "DELETE FROM items WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting item: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Item> getLowStockItems(int threshold) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE quantity <= ? ORDER BY quantity ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, threshold);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) items.add(mapRow(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching low stock: " + e.getMessage(), e);
        }
        return items;
    }

    @Override
    public List<Item> getItemsByCategory(String category) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items WHERE category = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) items.add(mapRow(rs));

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching by category: " + e.getMessage(), e);
        }
        return items;
    }

    private Item mapRow(ResultSet rs) throws SQLException {
        return new Item(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("category"),
            rs.getInt("quantity"),
            rs.getDouble("price"),
            rs.getString("supplier")
        );
    }
}
