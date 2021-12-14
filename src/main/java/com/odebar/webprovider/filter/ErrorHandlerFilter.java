package com.odebar.webprovider.filter;

import com.odebar.webprovider.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(req, resp);
        } catch (Throwable th) {
            String requestUrl = ((HttpServletRequest) req).getRequestURI();
            //LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
            RoutingUtils.forwardToPage("error.jsp", ((HttpServletRequest) req), ((HttpServletResponse) resp));
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
