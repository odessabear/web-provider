package com.odebar.webprovider.repository.entity;

import java.math.BigDecimal;

public class Tariff extends AbstractEntity<Integer> {
    private String name;
    private String description;
    private String imageLink;
    private BigDecimal price;
    private String category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("Tariff [id=%s, name=%s, description=%s, imageLink=%s, price=%s, category=%s]", getId(), name, description, imageLink, price, category);
    }
}
