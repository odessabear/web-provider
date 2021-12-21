package com.odebar.webprovider.repository.entity;

public class OrderItem extends AbstractEntity<Integer> {
    private Integer orderId;
    private Tariff tariff;

    public OrderItem(Tariff tariff) {
        this.tariff = tariff;
    }

    public OrderItem() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        return String.format("OrderItem [id=%s, idOrder=%s, product=%s, count=%s]", getId(), orderId, tariff);
    }
}
