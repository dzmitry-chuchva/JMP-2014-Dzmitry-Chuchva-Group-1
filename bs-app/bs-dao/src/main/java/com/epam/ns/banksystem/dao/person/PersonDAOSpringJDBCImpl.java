package com.epam.ns.banksystem.dao.person;

import com.epam.ns.banksystem.domain.person.PersonTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "personDAO")
public class PersonDAOSpringJDBCImpl implements PersonDAO {
    public static final String SQL_SELECT_PERSON_BY_ID =
        "SELECT p.person_id, p.first_name, p.last_name FROM persons p WHERE p.person_id = ? AND p.deleted = 0";
    public static final String SQL_SELECT_PERSONS_BY_BANK_ID =
        "SELECT p.person_id, p.first_name, p.last_name FROM persons p " +
        "JOIN accounts a ON a.person_id = p.person_id WHERE a.bank_id = ? AND p.deleted = 0 AND a.deleted = 0";

    private JdbcTemplate jdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PersonTO selectPerson(Long personId) {
        PersonTO personTO = jdbcTemplate.queryForObject(SQL_SELECT_PERSON_BY_ID, new PersonMapper(), personId);
        return personTO;
    }

    @Override
    public List<PersonTO> selectPersons(Long bankId) {
        List<PersonTO> personTOs = jdbcTemplate.query(SQL_SELECT_PERSONS_BY_BANK_ID, new PersonMapper(), bankId);
        return personTOs;
    }

    private static final class PersonMapper implements RowMapper<PersonTO> {
        @Override
        public PersonTO mapRow(ResultSet rs, int rowNum) throws SQLException {
            PersonTO personTO = new PersonTO();
            personTO.setId(rs.getLong("person_id"));
            personTO.setFirstName(rs.getString("first_name"));
            personTO.setLastName(rs.getString("last_name"));
            return personTO;
        }
    }
}
