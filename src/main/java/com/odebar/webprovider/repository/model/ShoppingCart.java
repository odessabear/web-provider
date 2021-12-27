package com.odebar.webprovider.repository.model;


import com.odebar.webprovider.Constants;
import com.odebar.webprovider.exception.ValidationException;
import com.odebar.webprovider.repository.entity.Tariff;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    private final Map<Integer, ShoppingCartItem> tariffs = new LinkedHashMap<>();
    private int totalCount = 0;
    private BigDecimal totalCost = BigDecimal.ZERO;

    public void addTariff(Tariff tariff, int count) {
        validateShoppingCartSize(tariff.getId());
        ShoppingCartItem shoppingCartItem = tariffs.get(tariff.getId());
        if (shoppingCartItem == null) {
            validateTariffCount(count);
            shoppingCartItem = new ShoppingCartItem(tariff, count);
            tariffs.put(tariff.getId(), shoppingCartItem);
        } else {
            validateTariffCount(count + shoppingCartItem.getCount());
            shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
        }
        refreshStatistics();
    }

    public void removeTariff(Integer tariffId, int count) {
        ShoppingCartItem shoppingCartItem = tariffs.get(tariffId);
        if (shoppingCartItem != null) {
            tariffs.remove(tariffId);
        }
        refreshStatistics();

    }

    public Collection<ShoppingCartItem> getItems() {
        return tariffs.values();
    }

    public int getTotalCount() {
        return totalCount;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    private void validateTariffByCategory(String category) {
        if (category != null) {
            throw new ValidationException("You can choose only one tariff in this category!");
        }
    }

    private void validateTariffCount(int count) {
        if (count > Constants.MAX_TARIFF_COUNT_PER_ONE_SHOPPING_CART) {
            throw new ValidationException("You can choose only " + (count - 1) + " tariff");
        }
    }

    private void validateShoppingCartSize(int tariffId) {
        if (tariffs.size() > Constants.MAX_DIFFERENT_TARIFFS_PER_SHOPPING_CART ||
                (tariffs.size() == Constants.MAX_DIFFERENT_TARIFFS_PER_SHOPPING_CART && !tariffs.containsKey(tariffId))) {
            throw new ValidationException("Limit for ShoppingCart size reached: size=" + tariffs.size());
        }
    }

    private void refreshStatistics() {
        totalCount = 0;
        totalCost = BigDecimal.ZERO;
        for (ShoppingCartItem shoppingCartItem : getItems()) {
            totalCount += shoppingCartItem.getCount();
            totalCost = totalCost.add(shoppingCartItem.getTariff().getPrice().multiply(BigDecimal.valueOf(shoppingCartItem.getCount())));
        }
    }

    @Override
    public String toString() {
        return String.format("ShoppingCart [products=%s, totalCount=%s, totalCost=%s]", tariffs, totalCount, totalCost);
    }

}
