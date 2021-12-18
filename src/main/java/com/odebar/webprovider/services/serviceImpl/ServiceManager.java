package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.services.OrderService;
import com.odebar.webprovider.services.TariffsService;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServiceManager {

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager(context);
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    public TariffsService getTariffsService() {
        return tariffService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public String getApplicationProperty(String key) {
        return applicationProperties.getProperty(key);
    }

    private final Properties applicationProperties = new Properties();
    private final TariffsService tariffService;
    private final OrderService orderService;


    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        tariffService = new TariffsServiceImpl();
        orderService = new OrderServiceImpl();
    }

    private void loadApplicationProperties(){
        try(InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
    }


}
