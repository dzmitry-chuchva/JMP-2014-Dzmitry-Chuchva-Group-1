package com.epam.nikitasidorevich.banksystem.dao.person;

import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;

import java.util.List;

public interface PersonDAO {

    PersonTO selectPerson(Long personId) throws DAOException;

    List<PersonTO> selectPersons(Long bankId) throws DAOException;
}
