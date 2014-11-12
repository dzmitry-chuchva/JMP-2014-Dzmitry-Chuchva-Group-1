package com.epam.nikitasidorevich.banksystem.person.service;

import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.person.dao.PersonDAO;
import com.epam.nikitasidorevich.banksystem.person.dao.PersonDAOImpl;
import com.epam.nikitasidorevich.banksystem.person.entity.PersonTO;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public List<PersonTO> fetchPersons(Long bankId) throws ServiceException {
        PersonDAO personDAO = new PersonDAOImpl();
        try {
            List<PersonTO> personTOs = personDAO.selectPersons(bankId);
            return personTOs;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
