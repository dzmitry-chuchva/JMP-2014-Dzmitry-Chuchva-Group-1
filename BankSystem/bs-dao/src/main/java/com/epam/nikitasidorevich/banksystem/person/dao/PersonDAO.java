package com.epam.nikitasidorevich.banksystem.person.dao;

import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.person.entity.PersonTO;

import java.util.List;

public interface PersonDAO {

    List<PersonTO> selectPersons(Long bankId) throws DAOException;
}
