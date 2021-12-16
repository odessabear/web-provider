package com.odebar.webprovider.repository.model;


import com.odebar.webprovider.Constants;
import com.odebar.webprovider.exception.ValidationException;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private final Map<Integer, ShoppingCartItem> tariffs = new HashMap<>();
    private int totalCount = 0;

    public void addTariff(int idProduct, int count) {
        validateShoppingCartSize(idProduct);
        ShoppingCartItem shoppingCartItem = tariffs.get(idProduct);
        if (shoppingCartItem == null) {
            validateProductCount(count);
            shoppingCartItem = new ShoppingCartItem(idProduct, count);
            tariffs.put(idProduct, shoppingCartItem);
        }
        else {
            validateProductCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
        }
        refreshStatistics();
    }

    public void removeProduct(Integer idProduct, int count) {
        ShoppingCartItem shoppingCartItem = tariffs.get(idProduct);
        if (shoppingCartItem != null) {
            tariffs.remove(idProduct);
        }
        refreshStatistics();

    }

    public Collection<ShoppingCartItem> getItems() {
        return tariffs.values();
    }

    public int getTotalCount() {
        return totalCount;
    }

    private void validateProductCount(int count) {
        if (count > Constants.MAX_PRODUCT_COUNT_PER_ONE_SHOPPING_CART) {
            throw new ValidationException("Limit for product count reached: count=" + count);
        }
    }

    private void validateShoppingCartSize(int idProduct) {
        if (tariffs.size() > Constants.MAX_DIFFERENT_PRODUCTS_PER_SHOPPING_CART ||
                (tariffs.size() == Constants.MAX_DIFFERENT_PRODUCTS_PER_SHOPPING_CART && !tariffs.containsKey(idProduct))) {
            throw new ValidationException("Limit for ShoppingCart size reached: size=" + tariffs.size());
        }
    }

    private void refreshStatistics() {
        totalCount = 0;
        for (ShoppingCartItem shoppingCartItem : getItems()) {
            totalCount += shoppingCartItem.getCount();
        }
    }

    @Override
    public String toString() {
        return String.format("ShoppingCart [products=%s, totalCount=%s]", tariffs, totalCount);
    }

}
