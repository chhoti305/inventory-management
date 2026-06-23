package com.inventory.model;

import java.time.LocalDateTime;

/**
 * Represents a stock transaction (IN/OUT) for inventory tracking.
 */
public class Transaction {

    public enum TransactionType { IN, OUT }

    private int id;
    private int itemId;
    private TransactionType type;
    private int quantity;
    private LocalDateTime timestamp;
    private String remarks;

    public Transaction() {}

    public Transaction(int id, int itemId, TransactionType type, int quantity,
                       LocalDateTime timestamp, String remarks) {
        this.id = id;
        this.itemId = itemId;
        this.type = type;
        this.quantity = quantity;
        this.timestamp = timestamp;
        this.remarks = remarks;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public TransactionType getType() { return type; }
    public void setType(TransactionType type) { this.type = type; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    @Override
    public String toString() {
        return "Transaction{id=" + id + ", itemId=" + itemId + ", type=" + type +
               ", quantity=" + quantity + ", timestamp=" + timestamp + "}";
    }
}
