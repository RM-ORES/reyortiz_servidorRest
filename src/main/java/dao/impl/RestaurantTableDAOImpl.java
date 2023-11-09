package dao.impl;

import dao.RestaurantTableDAO;
import dao.common.Constantes;
import dao.common.DBConnectionPool;
import dao.common.SqlQueries;
import domain.modelo.errores.DataBaseDownException;
import domain.modelo.errores.WrongStatementException;
import domain.modelo.restaurant.RestaurantTable;
import jakarta.inject.Inject;
import lombok.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class RestaurantTableDAOImpl implements RestaurantTableDAO {

    private final DBConnectionPool dbConnection;

    @Inject
    public RestaurantTableDAOImpl(DBConnectionPool dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<RestaurantTable> getAll() {
        List<RestaurantTable> list = null;
        try(Connection connection = dbConnection.getConnection()){
            try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){
                ResultSet resultSet = statement.executeQuery(SqlQueries.TABLEGETALL);
                List<RestaurantTable> read = readRS(resultSet);
                if (!read.isEmpty()){
                    list = read;
                }
            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e){
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return list;
    }

    @Override
    public RestaurantTable get(int id) {
        RestaurantTable table = null;
        try(Connection connection = dbConnection.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.TABLEGET)) {

                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();

                List<RestaurantTable> read = readRS(resultSet);
                if (!read.isEmpty()) {
                    table = read.get(0);
                }
            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e){
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return table;
    }

    @Override
    public int add(RestaurantTable restaurantTable) {
        int add = 0;
        try(Connection connection = dbConnection.getConnection()){
            try(PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.TABLEADD, Statement.RETURN_GENERATED_KEYS)){

                preparedStatement.setInt(1, restaurantTable.getTableNumber());
                preparedStatement.setInt(1, restaurantTable.getSeats());
                add = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e){
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return add;
    }

    @Override
    public int update(RestaurantTable restaurantTable) {
        int update = 0;
        try(Connection connection = dbConnection.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.ORDERUPDATE)) {

                preparedStatement.setInt(1, restaurantTable.getSeats());
                update = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e){
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return update;
    }

    @Override
    public int delete(int id) {
        int either = 0;
        try(Connection connection = dbConnection.getConnection()){
            try (PreparedStatement preparedStatementTable = connection.prepareStatement(SqlQueries.TABLEDELETE);
                 PreparedStatement preparedStatementOrder = connection.prepareStatement(SqlQueries.ORDERDELETEBYTABLE)) {

                preparedStatementTable.setInt(1, id);
                preparedStatementOrder.setInt(1, id);

                connection.setAutoCommit(false);
                preparedStatementOrder.executeUpdate();
                int result = preparedStatementTable.executeUpdate();

                connection.commit();
                either = result;

            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                throw new WrongStatementException(e.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e){
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return either;
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
