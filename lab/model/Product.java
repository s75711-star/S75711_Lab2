package com.lab.model;

/**
 * Product JavaBean representing the product entity.
 */
public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;

    // 1. Default Constructor (Required for JavaBeans)
    public Product() {
    }

    // 2. Parameterized Constructor (Without ID - used for Inserting new products)
    public Product(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // 3. Parameterized Constructor (With ID - used for Update and View)
    public Product(int id, String name, String category, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // 4. Getter and Setter Methods
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}