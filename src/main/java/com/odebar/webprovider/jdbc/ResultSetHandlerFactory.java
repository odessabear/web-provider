package com.odebar.webprovider.jdbc;

import com.odebar.webprovider.repository.entity.Category;
import com.odebar.webprovider.repository.entity.Tariff;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetHandlerFactory {

    public static final ResultSetHandler<Tariff> TARIFF_RESULT_SET_HANDLER = resultSet -> {
        Tariff tariff = new Tariff();
        tariff.setId(resultSet.getInt("id"));
        tariff.setName(resultSet.getString("name"));
        tariff.setCategory(resultSet.getString("category"));
        tariff.setDescription(resultSet.getString("description"));
        tariff.setImageLink(resultSet.getString("image_link"));
        tariff.setPrice(resultSet.getBigDecimal("price"));
        return tariff;
    };

    public static final ResultSetHandler<Category> CATEGORIES_RESULT_SET_HANDLER = resultSet -> {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setName(resultSet.getString("name"));
        category.setUrl(resultSet.getString("url"));
        return category;
    };

    public static <T> ResultSetHandler<T> getSingleResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return rs -> {
            if (rs.next()) {
                return oneRowResultSetHandler.handle(rs);
            } else {
                return null;
            }
        };
    }

    public static <T> ResultSetHandler<List<T>> getListResultSetHandler(final ResultSetHandler<T> oneRowResultSetHandler) {
        return rs -> {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(oneRowResultSetHandler.handle(rs));
            }
            return list;
        };
    }

    private ResultSetHandlerFactory() {
    }
}
