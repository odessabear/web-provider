package com.odebar.webprovider.servlet;

import com.odebar.webprovider.services.BusinessService;
import com.odebar.webprovider.services.ServiceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractController extends HttpServlet {

    private BusinessService businessService;

    @Override
    public final void init(){
        businessService = ServiceManager.getInstance(getServletContext()).getBusinessService();
    }

    public final BusinessService getBusinessService() {
        return businessService;
    }
}
