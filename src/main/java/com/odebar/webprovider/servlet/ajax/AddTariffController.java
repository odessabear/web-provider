package com.odebar.webprovider.servlet.ajax;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.util.SessionUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax/json/tariff/add")
public class AddTariffController extends AbstractTariffController {

    @Override
    protected void processTariffForm(TariffForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) {
        getOrderService().addTariffToShoppingCart(form, shoppingCart);
        String cookieValue = getOrderService().serializeShoppingCartToString(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
    }
}
