package com.odebar.webprovider.filters;

import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.repository.model.ShoppingCartItem;
import com.odebar.webprovider.util.SessionUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AutoRestoreShoppingCartFilter")
public class AutoRestoreShoppingCartFilter extends AbstractFilter {
    private static final String SHOPPING_CARD_DESERIALIZATION_DONE = "SHOPPING_CARD_DESERIALIZATION_DONE";


    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null) {
            if (!SessionUtils.isCurrentShoppingCartCreated(request)) {
                Cookie cookie = SessionUtils.findShoppingCartCookie(request);
                if (cookie != null) {
                    ShoppingCart shoppingCart = shoppingCartFromString(cookie.getValue());
                    SessionUtils.setCurrentShoppingCart(request, shoppingCart);
                }
            }
            request.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
        }

        filterChain.doFilter(request, response);
    }

    protected ShoppingCart shoppingCartFromString(String cookieValue) {
        return getShoppingCart(cookieValue);
    }

    public static ShoppingCart getShoppingCart(String cookieValue) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = cookieValue.split("\\|");
        for (String item : items) {
            String[] data = item.split("-");
            try {
                int idTariff = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                shoppingCart.addTariff(idTariff, count);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return shoppingCart;
    }

    protected String shoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            res.append(shoppingCartItem.getIdTariff()).append("-").append(shoppingCartItem.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    /*
    ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
            String cookieValue = shoppingCartToString(shoppingCart);
            SessionUtils.updateCurrentShoppingCartCookie(cookieValue, resp);
     */

}
