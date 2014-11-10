package com.epam.nikitasidorevich.banksystem.bank.dao;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BankDAOImpl extends NamedParameterJdbcDaoSupport implements BankDAO {
    public static final String SQL_SELECT_BANKS = "SELECT b.bank_id, b.name FROM banks b WHERE b.deleted = 0";

    @Override
    public List<BankTO> selectBanks() {
        List<BankTO> bankTOs = getJdbcTemplate().query(SQL_SELECT_BANKS, new BankMapper());
        return bankTOs;
    }

    private static final class BankMapper implements RowMapper<BankTO> {
        @Override
        public BankTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            BankTO bankTO = new BankTO();
            bankTO.setId(rs.getLong("bank_id"));
            bankTO.setName(rs.getString("name"));
            return bankTO;
        }
    }
}
