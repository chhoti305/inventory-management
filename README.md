# Inventory Management & Optimization System

A robust, object-oriented inventory management system built with **Java**, **MySQL**, and **Maven**, following the **DAO (Data Access Object)** design pattern.

## Features
- Full CRUD operations for inventory items
- Stock transaction tracking (IN / OUT)
- Low stock alert system
- Category-wise stock value aggregation using `HashMap`
- Items sorted by total value using `Collections`
- Summary dashboard with key metrics
- Exception validation boundaries for data integrity

## Tech Stack
| Layer | Technology |
|---|---|
| Language | Java 11 |
| Database | MySQL 8 |
| Build Tool | Maven |
| IDE | Eclipse |
| Pattern | DAO Design Pattern |

## Project Structure
```
src/
├── main/java/com/inventory/
│   ├── model/         # Item, Transaction entities
│   ├── dao/           # DAO interfaces and MySQL implementations
│   ├── service/       # Business logic layer
│   ├── util/          # DBConnection utility
│   └── Main.java      # Entry point
└── resources/
    └── schema.sql     # MySQL schema + seed data
```

## Setup & Run

### 1. Database Setup
```sql
mysql -u root -p < src/main/resources/schema.sql
```

### 2. Configure DB credentials
Edit `src/main/java/com/inventory/util/DBConnection.java`:
```java
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

### 3. Build & Run
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.inventory.Main"
```

## Key Design Decisions
- **DAO Pattern**: Isolates database access from business logic for clean, testable code
- **HashMap**: O(n) category-wise aggregation of large transactional datasets
- **PreparedStatements**: Prevents SQL injection and improves query performance
- **Indexed Columns**: `category` and `quantity` indexed for fast filtering queries
