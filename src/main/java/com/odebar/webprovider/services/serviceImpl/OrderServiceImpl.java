package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.exception.InternalServerErrorException;
import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.jdbc.JDBCUtils;
import com.odebar.webprovider.jdbc.ResultSetHandler;
import com.odebar.webprovider.jdbc.ResultSetHandlerFactory;
import com.odebar.webprovider.repository.entity.Tariff;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.repository.model.ShoppingCartItem;
import com.odebar.webprovider.services.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

class OrderServiceImpl implements OrderService {
    public static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class);

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
                throw new InternalServerErrorException("Tariff is not found by this id=" + tariffForm.getTariffId());
            }
            shoppingCart.addTariff(tariff, tariffForm.getCount());
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    public String serializeShoppingCartToString(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem shoppingCartItem : shoppingCart.getItems()) {
            res.append(shoppingCartItem.getTariff()).append("-").append(shoppingCartItem.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    @Override
    public ShoppingCart deserializeShoppingCart(String string) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = string.split("\\|");
        for (String item : items) {
            try {
                String[] data = item.split("-");
                int idTariff = Integer.parseInt(data[0]);
                int count = Integer.parseInt(data[1]);
                addTariffToShoppingCart(new TariffForm(idTariff, count), shoppingCart);
            } catch (RuntimeException e) {
                LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
            }
        }
        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
    }

    @Override
    public void removeTariffFromShoppingCart(TariffForm form, ShoppingCart shoppingCart) {
        shoppingCart.removeTariff(form.getTariffId(), form.getCount());
    }
}
