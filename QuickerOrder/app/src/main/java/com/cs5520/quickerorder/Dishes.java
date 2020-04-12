package com.cs5520.quickerorder;

import androidx.annotation.DrawableRes;

public class Dishes extends Item {
    private int id;
    private String name;
    private @DrawableRes int pic;
    private String description;

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dishes(int id, String name, @DrawableRes int pic, double price, String description) {
        this.id = id;
        this.name = name;
        this.pic = pic;
        this.price = price;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dishes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic=" + pic +
                ", price=" + price +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(@DrawableRes int pic) {
        this.pic = pic;
    }
}
