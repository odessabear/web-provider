package com.odebar.webprovider.servlet;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.services.OrderService;
import com.odebar.webprovider.services.TariffsService;
import com.odebar.webprovider.services.serviceImpl.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractController extends HttpServlet {

    private TariffsService productService;
    private OrderService orderService;

    @Override
    public final void init() throws ServletException {
        productService = ServiceManager.getInstance(getServletContext()).getTariffsService();
        orderService = ServiceManager.getInstance(getServletContext()).getOrderService();
    }

    public final TariffsService getTariffService() {
        return productService;
    }

    public final OrderService getOrderService() {
        return orderService;
    }

    public final int getPageCount(int totalCount, int itemsPerPage) {
        int result = totalCount / itemsPerPage;
        if (result * itemsPerPage != totalCount) {
            result++;
        }
        return result;
    }

    public final int getPage(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    public final TariffForm createTariffForm(HttpServletRequest request) {
        return new TariffForm(
                Integer.parseInt(request.getParameter("idTariff")),
                Integer.parseInt(request.getParameter("count"))
        );
    }
}
