package com.odebar.webprovider.listeners;

import com.odebar.webprovider.services.serviceImpl.ServiceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    public static final Logger LOGGER = LogManager.getLogger(ApplicationListener.class);
    private ServiceManager serviceManager;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
        } catch (RuntimeException e) {
            LOGGER.error("Web application 'web-provider' init is failed: " + e.getMessage(), e);
            throw e;
        }
        LOGGER.info("Web application 'web-provider' is initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Web application 'web-provider' is destroyed");
    }
}
