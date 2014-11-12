package com.epam.nikitasidorevich.banksystem.currency.dao;

import com.epam.nikitasidorevich.banksystem.connectionpool.ConnectionPool;
import com.epam.nikitasidorevich.banksystem.connectionpool.exception.ConnectionPoolException;
import com.epam.nikitasidorevich.banksystem.currency.entity.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDAOImpl implements CurrencyDAO {
    public static final String SQL_SELECT_CURRENCY_BY_ID = "SELECT c.currency_id, c.code, c.full_name FROM currencies c WHERE c.currency_id = ?";

    private CurrencyTO buildCurrency(ResultSet resultSet) throws SQLException {
        CurrencyTO currencyTO = new CurrencyTO();
        currencyTO.setId(resultSet.getLong("currency_id"));
        currencyTO.setCode(resultSet.getString("code"));
        currencyTO.setFullName(resultSet.getString("full_name"));
        return currencyTO;
    }

    @Override
    public CurrencyTO selectCurrency(Long currencyId) throws DAOException {
        CurrencyTO currencyTO = null;
        try (
            ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_CURRENCY_BY_ID);
        ) {
            preparedStatement.setLong(1, currencyId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                currencyTO = buildCurrency(resultSet);
            }
            return currencyTO;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }
}
