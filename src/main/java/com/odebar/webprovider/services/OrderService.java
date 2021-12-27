package com.odebar.webprovider.services;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;

public interface OrderService {

    void addTariffToShoppingCart(TariffForm tariffForm, ShoppingCart shoppingCart);

    String serializeShoppingCartToString(ShoppingCart shoppingCart);

    ShoppingCart deserializeShoppingCart(String string);

    void removeTariffFromShoppingCart(TariffForm form, ShoppingCart shoppingCart);
}
