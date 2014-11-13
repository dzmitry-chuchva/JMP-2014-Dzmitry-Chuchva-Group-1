package com.epam.nikitasidorevich.banksystem.dao.currency;

import com.epam.nikitasidorevich.banksystem.dao.connectionpool.ConnectionPool;
import com.epam.nikitasidorevich.banksystem.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAOImpl implements CurrencyDAO {
    private static final CurrencyDAOImpl INSTANCE = new CurrencyDAOImpl();

    private static final String SQL_SELECT_CURRENCY_BY_ID = "SELECT c.currency_id, c.code, c.full_name FROM currencies c WHERE c.currency_id = ?";
    private static final String SQL_SELECT_CURRENCIES = "SELECT c.currency_id, c.code, c.full_name FROM currencies c WHERE c.deleted = 0";

    private CurrencyTO buildCurrency(ResultSet resultSet) throws SQLException {
        CurrencyTO currencyTO = new CurrencyTO();
        currencyTO.setId(resultSet.getLong("currency_id"));
        currencyTO.setCode(resultSet.getString("code"));
        currencyTO.setFullName(resultSet.getString("full_name"));
        return currencyTO;
    }

    private CurrencyDAOImpl() {
    }

    public static CurrencyDAOImpl getInstance() {
        return INSTANCE;
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

    @Override
    public List<CurrencyTO> selectCurrencies() throws DAOException {
        List<CurrencyTO> currencyTOs = new ArrayList<>();
        try (
            ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_CURRENCIES);
        ) {
            while (resultSet.next()) {
                currencyTOs.add(buildCurrency(resultSet));
            }
            return currencyTOs;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }
}
