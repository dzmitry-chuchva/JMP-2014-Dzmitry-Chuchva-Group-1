package com.epam.ns.banksystem.dao.currency;

import com.epam.ns.banksystem.domain.currency.CurrencyTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "currencyDAO")
public class CurrencyDAOSpringJDBCImpl implements CurrencyDAO {
    public static final String SQL_SELECT_CURRENCY_BY_ID =
        "SELECT c.currency_id, c.code, c.full_name FROM currencies c WHERE c.currency_id = ?";
    public static final String SQL_SELECT_CURRENCIES =
        "SELECT c.currency_id, c.code, c.full_name FROM currencies c WHERE c.deleted = 0";

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CurrencyTO selectCurrency(Long currencyId) {
        CurrencyTO currencyTO = jdbcTemplate.queryForObject(SQL_SELECT_CURRENCY_BY_ID, new CurrencyMapper(), currencyId);
        return currencyTO;
    }

    @Override
    public List<CurrencyTO> selectCurrencies() {
        List<CurrencyTO> currencyTOs = jdbcTemplate.query(SQL_SELECT_CURRENCIES, new CurrencyMapper());
        return currencyTOs;
    }

    private static final class CurrencyMapper implements RowMapper<CurrencyTO> {
        @Override
        public CurrencyTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            CurrencyTO currencyTO = new CurrencyTO();
            currencyTO.setId(rs.getLong("currency_id"));
            currencyTO.setCode(rs.getString("code"));
            currencyTO.setFullName(rs.getString("full_name"));
            return currencyTO;
        }
    }
}
