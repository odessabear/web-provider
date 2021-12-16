package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.services.OrderService;
import com.odebar.webprovider.services.TariffsService;

import javax.servlet.ServletContext;

public class ServiceManager {

    private final TariffsService tariffService;
    private final OrderService orderService;

    private ServiceManager(ServletContext context) {
        tariffService = new TariffsServiceImpl();
        orderService = new OrderServiceImpl();
    }

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public void close() {
    }

    public TariffsService getTariffsService() {
        return tariffService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

}
