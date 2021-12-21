package com.odebar.webprovider.repository.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order extends AbstractEntity<Integer> {
    private Integer userId;
    private List<OrderItem> items;
    private Timestamp created;

    public Order() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public BigDecimal getTotalCost() {
        BigDecimal cost = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItem item : items) {
                cost = cost.add(item.getTariff().getPrice());
            }
        }
        return cost;
    }

    @Override
    public String toString() {
        return String.format("Order [id=%s, idAccount=%s, items=%s, created=%s]", getId(), userId, items, created);
    }
}
