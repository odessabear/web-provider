package com.odebar.webprovider.servlet.page;

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

@WebServlet("/tariffs")
public class AllTariffsController extends AbstractController {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Tariff> tariffs = getTariffService().tariffsList(1, Constants.MAX_TARIFFS_PER_ONE_HTML_PAGE);
        req.setAttribute("tariffs", tariffs);
        int totalCount = getTariffService().countAllTariffs();
        req.setAttribute("pageCount", getPageCount(totalCount, Constants.MAX_TARIFFS_PER_ONE_HTML_PAGE));
        RoutingUtils.forwardToPage("tariffs.jsp", req, resp);
    }
}
