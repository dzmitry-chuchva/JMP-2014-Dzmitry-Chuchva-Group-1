package com.epam.ns.banksystem.dao.bank;

import com.epam.ns.banksystem.domain.bank.BankTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "bankDAO")
public class BankDAOSpringJDBCImpl implements BankDAO {
    public static final String SQL_SELECT_BANKS = "SELECT b.bank_id, b.name FROM banks b WHERE b.deleted = 0";

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public BankTO selectBank(Long bankId) {
        return null;
    }

    @Override
    public List<BankTO> selectBanks() {
        List<BankTO> bankTOs = jdbcTemplate.query(SQL_SELECT_BANKS, new BankMapper());
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
