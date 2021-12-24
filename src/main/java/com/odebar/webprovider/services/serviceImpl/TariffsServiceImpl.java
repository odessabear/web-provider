package com.odebar.webprovider.services.serviceImpl;

import com.odebar.webprovider.exception.InternalServerErrorException;
import com.odebar.webprovider.jdbc.JDBCUtils;
import com.odebar.webprovider.jdbc.ResultSetHandler;
import com.odebar.webprovider.jdbc.ResultSetHandlerFactory;
import com.odebar.webprovider.repository.entity.Category;
import com.odebar.webprovider.repository.entity.Tariff;
import com.odebar.webprovider.services.TariffsService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class TariffsServiceImpl implements TariffsService {

    private static final ResultSetHandler<List<Tariff>> tariffResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.TARIFF_RESULT_SET_HANDLER);
    private static final ResultSetHandler<List<Category>> categoriesListResultSetHandler =
            ResultSetHandlerFactory.getListResultSetHandler(ResultSetHandlerFactory.CATEGORIES_RESULT_SET_HANDLER);
    public static final ResultSetHandler<Integer> countResultSetHandler = ResultSetHandlerFactory.getCountResultSetHandler();

    private final DataSource dataSource;

    public TariffsServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Tariff> tariffsList(int page, int limit) {
        try (Connection connection = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(connection, "select t.*, c.name as category from tariffs t, categories c " +
                    "where c.id=t.categories_id order by t.id limit ? offset ?", tariffResultSetHandler, limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public int countAllTariffs() {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select count(*) from tariffs", countResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Tariff> tariffsListByCategory(String categoryUrl, int page, int limit) {
        try (Connection connection = dataSource.getConnection()) {
            int offset = (page - 1) * limit;
            return JDBCUtils.select(connection, "select t.*, c.name as category from tariffs t, categories c " +
                    "where c.url=? and c.id=t.categories_id order by t.id limit ? offset ?", tariffResultSetHandler, categoryUrl, limit, offset);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }

    @Override
    public int countAllTariffsByCategory(String categoryUrl) {
        return 0;
    }

    @Override
    public List<Category> categoriesList() {
        try (Connection connection = dataSource.getConnection()) {
            return JDBCUtils.select(connection, "select * from categories order by id", categoriesListResultSetHandler);
        } catch (SQLException e) {
            throw new InternalServerErrorException("Can't execute sql query: " + e.getMessage(), e);
        }
    }
}
