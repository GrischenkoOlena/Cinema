package com.hryshchenko.cinema.model.entity;

import java.util.Objects;

public class Category extends Entity{
    private static final long serialVersionUID = 1L;
    private String category;
    private double price;

    public Category() {}

    public Category(long id, String category, double price) {
        super(id);
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
        Category other = (Category) o;
        return Double.compare(other.price, price) == 0 && Objects.equals(other.category, category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, price);
    }
}
