package com.odebar.webprovider.repository.entity;

import java.util.Objects;

public class Category extends AbstractEntity<Integer> {
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return String.format("Category [id = %s, name = %s url = %s]", getId(), name, url);
    }
}
