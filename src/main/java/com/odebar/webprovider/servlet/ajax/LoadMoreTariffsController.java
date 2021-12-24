package com.odebar.webprovider.servlet.ajax;

import com.odebar.webprovider.Constants;
import com.odebar.webprovider.repository.entity.Tariff;
import com.odebar.webprovider.servlet.AbstractController;
import com.odebar.webprovider.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ajax/html/more/tariffs")
public class LoadMoreTariffsController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tariff> tariffs = getTariffService().tariffsList(getPage(req), Constants.MAX_TARIFFS_PER_ONE_HTML_PAGE);
        req.setAttribute("tariffs", tariffs);
        RoutingUtils.forwardToFragment("tariffs-list.jsp", req, resp);
    }
}
