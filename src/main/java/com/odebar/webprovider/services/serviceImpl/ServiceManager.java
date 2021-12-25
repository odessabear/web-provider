package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.services.OrderService;
import com.odebar.webprovider.services.TariffsService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {

    public static final Logger LOGGER = LogManager.getLogger(ServiceManager.class);

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
    private final BasicDataSource dataSource;
    private final TariffsService tariffService;
    private final OrderService orderService;


    private ServiceManager(ServletContext context) {
        loadApplicationProperties();
        dataSource = createDataSource();
        tariffService = new TariffsServiceImpl(dataSource);
        orderService = new OrderServiceImpl(dataSource);
    }

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(false);
        dataSource.setRollbackOnReturn(true);
        dataSource.setDriverClassName(getApplicationProperty("db.driver"));
        dataSource.setUrl(getApplicationProperty("db.url"));
        dataSource.setUsername(getApplicationProperty("db.username"));
        dataSource.setPassword(getApplicationProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
        return dataSource;
    }

    private void loadApplicationProperties() {
        try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            LOGGER.error("Close datasource failed: " + e.getMessage(), e);
        }
    }


}
