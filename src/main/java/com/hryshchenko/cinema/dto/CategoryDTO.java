package com.hryshchenko.cinema.dto;

public class CategoryDTO implements ISimpleDTO {
    private static final long serialVersionUID = 1L;
    private int id;
    private String category;
    private double price;

    public CategoryDTO() {}

    public CategoryDTO(int id, String category, double price) {
        this.id = id;
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
