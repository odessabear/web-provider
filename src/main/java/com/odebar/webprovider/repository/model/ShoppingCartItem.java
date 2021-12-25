package com.odebar.webprovider.repository.model;

import com.odebar.webprovider.repository.entity.Tariff;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {

    private Tariff tariff;
    private int count;

    public ShoppingCartItem() {
        super();
    }

    public ShoppingCartItem(Tariff tariff, int count) {
        super();
        this.tariff = tariff;
        this.count = count;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("ShoppingCartItem [tariff=%s, count=%s]", tariff, count);
    }
}
