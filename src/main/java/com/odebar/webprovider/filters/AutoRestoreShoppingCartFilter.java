package com.odebar.webprovider.filters;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.services.OrderService;
import com.odebar.webprovider.services.serviceImpl.ServiceManager;
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

    private OrderService orderService;

    @Override
    public void init(FilterConfig filterConfig) {
        orderService = ServiceManager.getInstance(filterConfig.getServletContext()).getOrderService();
    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getSession().getAttribute(SHOPPING_CARD_DESERIALIZATION_DONE) == null) {
            if (!SessionUtils.isCurrentShoppingCartCreated(request)) {
                Cookie cookie = SessionUtils.findShoppingCartCookie(request);
                if (cookie != null) {
                    ShoppingCart shoppingCart = orderService.deserializeShoppingCart(cookie.getValue());
                    if (shoppingCart != null) {
                        SessionUtils.setCurrentShoppingCart(request, shoppingCart);
                    }
                }
            }
            request.getSession().setAttribute(SHOPPING_CARD_DESERIALIZATION_DONE, Boolean.TRUE);
        }

        filterChain.doFilter(request, response);
    }
}
