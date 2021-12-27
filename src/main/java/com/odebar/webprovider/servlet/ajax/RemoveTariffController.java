package com.odebar.webprovider.servlet.ajax;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.util.SessionUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ajax/json/tariff/remove")
public class RemoveTariffController extends AbstractTariffController {

    @Override
    protected void processTariffForm(TariffForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) {
        if (shoppingCart.getItems().isEmpty()) {
            SessionUtils.clearCurrentShoppingCart(req, resp);
        } else {
            String cookieValue = getOrderService().serializeShoppingCartToString(shoppingCart);
            SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
        }
    }
}
