package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.exception.InternalServerErrorException;
import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.jdbc.JDBCUtils;
import com.odebar.webprovider.jdbc.ResultSetHandler;
import com.odebar.webprovider.jdbc.ResultSetHandlerFactory;
import com.odebar.webprovider.repository.entity.Tariff;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.services.OrderService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class OrderServiceImpl implements OrderService {
    private static final ResultSetHandler<Tariff> tariffResultSetHandler =
            ResultSetHandlerFactory.getSingleResultSetHandler(ResultSetHandlerFactory.TARIFF_RESULT_SET_HANDLER);

    private final DataSource dataSource;

    public OrderServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addTariffToShoppingCart(TariffForm tariffForm, ShoppingCart shoppingCart) {
        try (Connection connection = dataSource.getConnection()) {
            Tariff tariff = JDBCUtils.select(connection, "select t.*, c.name as category from tariffs t, categories c " +
                    "where c.id=t.categories_id and t.id=?", tariffResultSetHandler, tariffForm.getTariffId());
            if (tariff == null) {
                throw new InternalServerErrorException("Tariff is not found bu this id=" + tariffForm.getTariffId());
            }
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }
}
