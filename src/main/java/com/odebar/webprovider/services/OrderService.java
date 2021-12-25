package com.odebar.webprovider.services;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;

public interface OrderService {

    void addTariffToShoppingCart(TariffForm tariffForm, ShoppingCart shoppingCart);
}
