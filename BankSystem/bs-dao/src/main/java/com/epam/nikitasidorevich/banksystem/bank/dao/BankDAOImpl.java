package com.epam.nikitasidorevich.banksystem.bank.dao;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.connectionpool.ConnectionPool;
import com.epam.nikitasidorevich.banksystem.connectionpool.exception.ConnectionPoolException;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BankDAOImpl implements BankDAO {
    public static final String SQL_SELECT_BANKS = "SELECT b.bank_id, b.name FROM banks b WHERE b.deleted = 0";

    private BankTO buildbuilBankFromResultSet(ResultSet resultSet) throws SQLException {
        BankTO bankTO = new BankTO();
        bankTO.setId(resultSet.getLong("bank_id"));
        bankTO.setName(resultSet.getString("name"));
        return bankTO;
    }

    @Override
    public List<BankTO> selectBanks() throws DAOException {
        List<BankTO> bankTOs = new ArrayList<BankTO>();

        ConnectionPool.ConnectionWrapper conn = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            conn = ConnectionPool.getInstance().getConnection();
            statement = conn.createStatement();
            rs = statement.executeQuery(SQL_SELECT_BANKS);
            while (rs.next()) {
                bankTOs.add(buildbuilBankFromResultSet(rs));
            }
            return bankTOs;
        } catch (ConnectionPoolException ex) {
            throw new DAOException("Connection error", ex);
        } catch (SQLException ex) {
            throw new DAOException("Statement error", ex);
        } finally {
            try {
                if (conn != null) {
                    ConnectionPool.getInstance().releaseConnection(conn);
                }
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (ConnectionPoolException e) {
                throw new DAOException("Connection error", e);
            } catch (SQLException e) {
                throw new DAOException("Resources error", e);
            }
        }
    }
}
