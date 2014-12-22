package com.epam.ns.banksystem.dao.account;

import com.epam.ns.banksystem.domain.account.AccountTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "accountDAO")
public class AccountDAOSpringJDBCImpl implements AccountDAO {
    private static final String SQL_SELECT_ACCOUNTS_BY_BANK_ID_AND_PERSON_ID =
        "SELECT a.account_id, a.bank_id, a.person_id, a.currency_id, a.total_cash FROM accounts a " +
        "WHERE a.deleted = 0 AND a.bank_id = ? AND a.person_id = ?";
    private static final String SQL_UPDATE_ACCOUNTS_TOTAL_CASH =
        "UPDATE accounts " +
        "SET currency_id = ?, total_cash = ? " +
        "WHERE deleted = 0 AND bank_id = ? AND account_id = ? AND person_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<AccountTO> selectAccounts(Long bankId, Long personId) {
        List<AccountTO> accountTOs = jdbcTemplate.query(SQL_SELECT_ACCOUNTS_BY_BANK_ID_AND_PERSON_ID, new AccountMapper(), bankId, personId);
        return accountTOs;
    }

    @Override
    public void batchUpdateAccounts(List<AccountTO> accountTOs) {
        List<Object[]> batch = new ArrayList<Object[]>();
        for (AccountTO accountTO : accountTOs) {
            Object[] values = new Object[] {
                accountTO.getCurrencyId(),
                accountTO.getTotalCash(),
                accountTO.getBankId(),
                accountTO.getId(),
                accountTO.getPersonId()
            };
            batch.add(values);
        }
        jdbcTemplate.batchUpdate(SQL_UPDATE_ACCOUNTS_TOTAL_CASH, batch);
    }

    private static final class AccountMapper implements RowMapper<AccountTO> {
        @Override
        public AccountTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            AccountTO accountTO = new AccountTO();
            accountTO.setId(rs.getLong("currency_id"));
            accountTO.setBankId(rs.getLong("bank_id"));
            accountTO.setPersonId(rs.getLong("person_id"));
            accountTO.setCurrencyId(rs.getLong("currency_id"));
            accountTO.setTotalCash(rs.getDouble("total_cash"));
            return accountTO;
        }
    }
}
