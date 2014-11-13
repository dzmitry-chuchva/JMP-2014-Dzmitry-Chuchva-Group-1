package com.epam.nikitasidorevich.banksystem.dao.person;

import com.epam.nikitasidorevich.banksystem.dao.connectionpool.ConnectionPool;
import com.epam.nikitasidorevich.banksystem.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private static final PersonDAOImpl INSTANCE = new PersonDAOImpl();

    private static final String SQL_SELECT_PERSON_BY_ID = "SELECT p.person_id, p.first_name, p.last_name FROM persons p WHERE p.person_id = ?";
    private static final String SQL_SELECT_PERSONS_BY_BANK_ID =
            "SELECT p.person_id, p.first_name, p.last_name FROM persons p JOIN accounts a ON a.person_id = p.person_id WHERE p.deleted = 0 AND a.deleted = 0 AND a.bank_id = ?";

    private PersonDAOImpl() {
    }

    public static PersonDAOImpl getInstance() {
        return INSTANCE;
    }

    private PersonTO buildPerson(ResultSet resultSet) throws SQLException {
        PersonTO personTO = new PersonTO();
        personTO.setId(resultSet.getLong("person_id"));
        personTO.setFirstName(resultSet.getString("first_name"));
        personTO.setLastName(resultSet.getString("last_name"));
        return personTO;
    }

    @Override
    public PersonTO selectPerson(Long personId) throws DAOException {
        PersonTO personTO = null;
        try (
            ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PERSON_BY_ID);
        ) {
            preparedStatement.setLong(1, personId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personTO = buildPerson(resultSet);
            }
            return personTO;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }

    @Override
    public List<PersonTO> selectPersons(Long bankId) throws DAOException {
        List<PersonTO> personTOs = new ArrayList<>();
        try (
                ConnectionPool.ConnectionWrapper connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PERSONS_BY_BANK_ID);
        ) {
            preparedStatement.setLong(1, bankId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personTOs.add(buildPerson(resultSet));
            }
            return personTOs;
        } catch (SQLException | ConnectionPoolException e) {
            e.printStackTrace();
            throw new DAOException("DAO exception", e);
        }
    }
}
