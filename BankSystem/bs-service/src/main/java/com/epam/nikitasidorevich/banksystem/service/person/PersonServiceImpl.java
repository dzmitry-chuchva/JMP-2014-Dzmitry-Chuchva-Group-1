package com.epam.nikitasidorevich.banksystem.service.person;

import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.dao.person.PersonDAO;
import com.epam.nikitasidorevich.banksystem.dao.person.PersonDAOImpl;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    @Override
    public PersonTO fetchPerson(Long personId) throws ServiceException {
        PersonDAO personDAO = PersonDAOImpl.getInstance();
        try {
            PersonTO personTO = personDAO.selectPerson(personId);
            return personTO;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }

    @Override
    public List<PersonTO> fetchPersons(Long bankId) throws ServiceException {
        PersonDAO personDAO = PersonDAOImpl.getInstance();
        try {
            List<PersonTO> personTOs = personDAO.selectPersons(bankId);
            return personTOs;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
