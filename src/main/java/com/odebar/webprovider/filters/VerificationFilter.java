package com.odebar.webprovider.filters;

import com.odebar.webprovider.services.BusinessService;
import com.odebar.webprovider.services.ServiceManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter("/*")
public class VerificationFilter implements Filter {
    private BusinessService businessService;

    @Override
    public void init(FilterConfig filterConfig){
        businessService = ServiceManager.getInstance(
                filterConfig.getServletContext()).getBusinessService();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        businessService.doSomething();
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
