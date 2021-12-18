package com.odebar.webprovider.servlet.page;

import com.odebar.webprovider.servlet.AbstractController;
import com.odebar.webprovider.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/error")
public class ErrorController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RoutingUtils.forwardToPage("error.jsp", req, resp);
    }
}
