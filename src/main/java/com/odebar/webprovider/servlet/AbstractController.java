package com.odebar.webprovider.servlet;

import com.odebar.webprovider.services.OrderService;
import com.odebar.webprovider.services.TariffsService;
import com.odebar.webprovider.services.serviceImpl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {

    private TariffsService productService;
    private OrderService orderService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getTariffsService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
    }

    public final TariffsService getProductService() {
        return productService;
    }

    public final OrderService getOrderService() {
        return orderService;
    }
}
