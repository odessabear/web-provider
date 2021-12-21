package com.odebar.webprovider.listeners;

import com.odebar.webprovider.services.serviceImpl.ServiceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebProviderApplicationListener implements ServletContextListener {
    public static final Logger LOGGER = LogManager.getLogger(WebProviderApplicationListener.class);

    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
        } catch (RuntimeException e) {
            LOGGER.error("Web application 'webprovider' init is failed: " + e.getMessage(), e);
            throw e;
        }
        LOGGER.info("Web application 'webprovider' is initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Web application 'webprovider' is destroyed");
    }
}
