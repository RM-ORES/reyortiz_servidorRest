package dao.impl;

import dao.RestaurantTableDAO;
import dao.common.Constantes;
import dao.common.DBConnectionPool;
import dao.common.SqlQueries;
import domain.modelo.errores.DataBaseDownException;
import domain.modelo.errores.NotFoundException;
import domain.modelo.errores.RollbackException;
import domain.modelo.errores.WrongStatementException;
import domain.modelo.restaurant.RestaurantTable;
import jakarta.inject.Inject;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Log4j2
public class RestaurantTableDAOImpl implements RestaurantTableDAO {

    private final DBConnectionPool dbConnection;

    @Inject
    public RestaurantTableDAOImpl(DBConnectionPool dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<RestaurantTable> getAll() {
        List<RestaurantTable> result;
        try (Connection connection = dbConnection.getConnection()) {
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                ResultSet resultSet = statement.executeQuery(SqlQueries.TABLEGETALL);
                List<RestaurantTable> read = readRS(resultSet);

                if (!read.isEmpty()) {
                    result = read;
                } else {
                    throw new NotFoundException(Constantes.NOT_FOUND);
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return result;
    }

    @Override
    public RestaurantTable get(int id) {
        RestaurantTable result;
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.TABLEGET)) {

                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                List<RestaurantTable> read = readRS(resultSet);
                if (!read.isEmpty()) {
                    result = read.get(Constantes.INDEX_0);
                } else {
                    throw new NotFoundException(Constantes.NOT_FOUND);
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return result;
    }

    @Override
    public int add(RestaurantTable restaurantTable) {
        int add;
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.TABLEADD, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, restaurantTable.getSeats());

                add = preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();

                if (rs.next()) {
                    int autoId = rs.getInt(1);
                    restaurantTable.setTableNumber(autoId);
                }
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return add;
    }

    @Override
    public int update(RestaurantTable restaurantTable) {
        int result;
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.TABLEUPDATE)) {

                preparedStatement.setInt(1, restaurantTable.getSeats());
                preparedStatement.setInt(2, restaurantTable.getTableNumber());

                result = preparedStatement.executeUpdate();
                if (result != 1) {
                    throw new NotFoundException(Constantes.NOT_FOUND);
                }

            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return result;
    }

    @Override
    public int delete(int id) {
        int result;
        try (Connection connection = dbConnection.getConnection()) {
            try (PreparedStatement preparedStatementTable = connection.prepareStatement(SqlQueries.TABLEDELETE);
                 PreparedStatement preparedStatementOrder = connection.prepareStatement(SqlQueries.ORDERDELETEBYTABLE)) {
                preparedStatementTable.setInt(1, id);
                preparedStatementOrder.setInt(1, id);

                connection.setAutoCommit(false);
                preparedStatementOrder.executeUpdate();
                result = preparedStatementTable.executeUpdate();

                if (result != 1) {
                    throw new NotFoundException(Constantes.NOT_FOUND);
                }
                connection.commit();

            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RollbackException(ex.getMessage());
                }
                throw new WrongStatementException(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return result;
    }

    private List<RestaurantTable> readRS(ResultSet resultSet) throws SQLException {
        List<RestaurantTable> list = new ArrayList<>();
        while (resultSet.next()) {
            int tableNumber = resultSet.getInt(SqlQueries.TABLE_NUMBER);
            int numberSeats = resultSet.getInt(SqlQueries.NUMBER_SEATS);

            list.add(new RestaurantTable(tableNumber, numberSeats));
        }

        return list;
    }
}
