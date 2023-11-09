package dao.impl;

import dao.RestaurantOrderDAO;
import dao.common.Constantes;
import dao.common.DBConnectionPool;
import dao.common.SqlQueries;
import domain.modelo.errores.DataBaseDownException;
import domain.modelo.errores.WrongStatementException;
import domain.modelo.restaurant.RestaurantOrder;

import jakarta.inject.Inject;
import lombok.Data;
import lombok.extern.log4j.Log4j2;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Data
@Log4j2
public class RestaurantOrderDAOImpl implements RestaurantOrderDAO {

    private final DBConnectionPool dbConnection;

    @Inject
    public RestaurantOrderDAOImpl(DBConnectionPool dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<RestaurantOrder> getAll() {
        List<RestaurantOrder> either = null;
        try (Connection connection = dbConnection.getConnection()){
            try (Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                ResultSet resultSet = statement.executeQuery(SqlQueries.ORDERGETALL);
                List<RestaurantOrder> read = readRS(resultSet);
                if (!read.isEmpty()) {
                    either = read;
                }
            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }
        return either;
    }

    @Override
    public RestaurantOrder get(int id) {
       RestaurantOrder either = null;
       try(Connection connection = dbConnection.getConnection()){
           try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.ORDERGET)) {
               preparedStatement.setInt(1, id);
               ResultSet resultSet = preparedStatement.executeQuery();

               List<RestaurantOrder> read = readRS(resultSet);
               if (!read.isEmpty()) {
                   either = read.get(0);
               }
           } catch (SQLException e) {
               throw new WrongStatementException(e.getMessage());
           }
       } catch (SQLException e) {
           throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
       }


        return either;
    }

    @Override
    public Integer add(RestaurantOrder restaurantOrder) {
        Integer either = null;

        try (Connection connection = dbConnection.getConnection()) {
            try(PreparedStatement preparedStatementOrder = connection.prepareStatement(SqlQueries.ORDERADD, Statement.RETURN_GENERATED_KEYS)){

                preparedStatementOrder.setInt(1, restaurantOrder.getTableNumber());
                preparedStatementOrder.setInt(2, restaurantOrder.getIdCustomer());
                preparedStatementOrder.setTimestamp(3, Timestamp.valueOf(restaurantOrder.getDate()));

                either = preparedStatementOrder.executeUpdate();

            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e) {
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }

        return either;
    }


    @Override
    public Integer update(RestaurantOrder restaurantOrder) {
        Integer either = null;
        try(Connection connection = dbConnection.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.ORDERUPDATE)) {

                preparedStatement.setInt(1, restaurantOrder.getTableNumber());
                preparedStatement.setInt(2, restaurantOrder.getIdCustomer());
                preparedStatement.setInt(3, restaurantOrder.getId());

                either = preparedStatement.executeUpdate();

            } catch (SQLException e) {
                throw new WrongStatementException(e.getMessage());
            }
        } catch (SQLException e){
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }

        return either;
    }

    @Override
    public Integer delete(int id) {
        Integer either = null;
        try (Connection connection = dbConnection.getConnection()) {

            try (PreparedStatement preparedStatementItem = connection.prepareStatement(SqlQueries.ORDERITEMDELETEBYORDER);
                 PreparedStatement preparedStatementOrder = connection.prepareStatement(SqlQueries.ORDERDELETE)) {

                preparedStatementItem.setInt(1, id);
                preparedStatementOrder.setInt(1, id);

                connection.setAutoCommit(false);
                preparedStatementItem.executeUpdate();
                int result = preparedStatementOrder.executeUpdate();

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
        } catch (SQLException e) {
            throw new DataBaseDownException(Constantes.NO_SE_HA_PODIDO_CONECTAR_A_LA_BASE_DE_DATOS);
        }

        return either;
    }

    private List<RestaurantOrder> readRS(ResultSet resultSet) throws SQLException {
        List<RestaurantOrder> list = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt(SqlQueries.IDORDER);
                int tableNumber = resultSet.getInt(SqlQueries.TABLE_NUMBER);
                int idcustomer = resultSet.getInt(SqlQueries.IDCUSTOMER);
                LocalDateTime date = LocalDateTime.ofInstant(resultSet.getTimestamp(SqlQueries.TIME_STAMP).toInstant(), ZoneId.systemDefault());

                list.add(new RestaurantOrder(id, tableNumber, idcustomer, date, null));
            }
        return list;
    }
}
