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
        serviceManager = ServiceManager.getInstance(sce.getServletContext());
        LOGGER.info("Web application 'web-provider' is initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Web application 'web-provider' is destroyed");
    }
}
