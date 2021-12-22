package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.exception.InternalServerErrorException;
import com.odebar.webprovider.jdbc.JDBCUtils;
import com.odebar.webprovider.jdbc.ResultSetHandler;
import com.odebar.webprovider.jdbc.ResultSetHandlerFactory;
import com.odebar.webprovider.repository.entity.Tariff;
import com.odebar.webprovider.services.TariffsService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class TariffsServiceImpl implements TariffsService {

    private static final ResultSetHandler<List<Tariff>> tariffResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.TARIFF_RESULT_SET_HANDLER);

    private final DataSource dataSource;

    public TariffsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tariff> tariffsList(int page, int limit) {
        try (Connection connection = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(connection, "select t.*, c.name as category, from tariff t, category c" +
                    "where c.id=t.id_category limit ? offset ?", tariffResultSetHandler, limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }
}
