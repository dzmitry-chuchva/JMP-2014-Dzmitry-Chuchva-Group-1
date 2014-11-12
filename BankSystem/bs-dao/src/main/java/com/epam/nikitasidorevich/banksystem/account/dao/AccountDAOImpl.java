package com.epam.nikitasidorevich.banksystem.account.dao;

import com.epam.nikitasidorevich.banksystem.account.entity.AccountTO;
import com.epam.nikitasidorevich.banksystem.connectionpool.ConnectionPool;
import com.epam.nikitasidorevich.banksystem.connectionpool.exception.ConnectionPoolException;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {
    public static final String SQL_SELECT_ACCOUNTS_BY_BANK_ID_AND_PERSON_ID =
            "SELECT a.account_id, a.bank_id, a.person_id, a.currency_id, a.total_cash FROM accounts a " +
            "WHERE a.deleted = 0 AND a.bank_id = ? AND a.person_id = ?";

    private AccountTO buildAccount(ResultSet resultSet) throws SQLException {
        AccountTO accountTO = new AccountTO();
        accountTO.setId(resultSet.getLong("account_id"));
        accountTO.setBankId(resultSet.getLong("bank_id"));
        accountTO.setPersonId(resultSet.getLong("person_id"));
        accountTO.setCurrencyId(resultSet.getLong("currency_id"));
        accountTO.setTotalCash(resultSet.getDouble("total_cash"));
        return accountTO;
    }

    @Override
    public List<AccountTO> selectAccounts(Long bankId, Long personId) throws DAOException {
        List<AccountTO> accountTOs = new ArrayList<>();
        try (
            ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ACCOUNTS_BY_BANK_ID_AND_PERSON_ID);
        ) {
            preparedStatement.setLong(1, bankId);
            preparedStatement.setLong(2, personId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountTOs.add(buildAccount(resultSet));
            }
            return accountTOs;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }
}
