package com.odebar.webprovider.repository.model;

import java.io.Serializable;

public class ShoppingCartItem implements Serializable {

    private int idTariff;
    private int count;

    public ShoppingCartItem() {
        super();
    }

    public ShoppingCartItem(int idProduct, int count) {
        super();
        this.idTariff = idProduct;
        this.count = count;
    }

    public int getIdTariff() {
        return idTariff;
    }

    public void setIdTariff(int idTariff) {
        this.idTariff = idTariff;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ShoppingCartItem [idProduct=" + idTariff + ", count=" + count + "]";
    }
}
