package com.epam.nikitasidorevich.banksystem.bank.dao;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.connectionpool.ConnectionPool;
import com.epam.nikitasidorevich.banksystem.connectionpool.exception.ConnectionPoolException;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankDAOImpl implements BankDAO {
    public static final String SQL_SELECT_BANK_BY_ID = "SELECT b.bank_id, b.name FROM banks b WHERE b.bank_id = ? AND b.deleted = 0";
    public static final String SQL_SELECT_BANKS = "SELECT b.bank_id, b.name FROM banks b WHERE b.deleted = 0";

    private BankTO buildBank(ResultSet resultSet) throws SQLException {
        BankTO bankTO = new BankTO();
        bankTO.setId(resultSet.getLong("bank_id"));
        bankTO.setName(resultSet.getString("name"));
        return bankTO;
    }

    @Override
    public BankTO selectBank(Long bankId) throws DAOException {
        BankTO bankTO = null;
        try (
            ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BANK_BY_ID);
        ) {
            preparedStatement.setLong(1, bankId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankTO = buildBank(resultSet);
            }
            return bankTO;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }

    @Override
    public List<BankTO> selectBanks() throws DAOException {
        List<BankTO> bankTOs = new ArrayList<>();
        try (
            ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BANKS);
        ) {
            while (resultSet.next()) {
                bankTOs.add(buildBank(resultSet));
            }
            return bankTOs;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }
}
