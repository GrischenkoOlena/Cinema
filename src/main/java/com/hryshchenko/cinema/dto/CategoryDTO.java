package com.hryshchenko.cinema.dto;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryDTO that = (CategoryDTO) o;
        return id == that.id && Double.compare(that.price, price) == 0 && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, price);
    }
}
