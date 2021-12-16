package com.odebar.webprovider.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.odebar.webprovider.util.UrlUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractFilter implements Filter {
    protected final Logger LOGGER = LogManager.getLogger(getClass());

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public final void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (UrlUtils.isMediaUrl(url) || UrlUtils.isStaticUrl(url)) {
            filterChain.doFilter(request, response);
        } else {
            doFilter(request, response, filterChain);
        }
    }

    public abstract void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException;

    @Override
    public void destroy() {
    }
}
