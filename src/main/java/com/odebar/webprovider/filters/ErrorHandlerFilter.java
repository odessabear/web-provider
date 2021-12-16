package com.odebar.webprovider.filters;

import com.odebar.webprovider.util.RoutingUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(request, response);
        } catch (Throwable th) {
            String requestUrl = request.getRequestURI();
            LOGGER.error("Request " + requestUrl + " failed: " + th.getMessage(), th);
            RoutingUtils.forwardToPage("error.jsp", request, response);
        }
    }
}
