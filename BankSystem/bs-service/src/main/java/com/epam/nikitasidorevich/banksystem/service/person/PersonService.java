package com.epam.nikitasidorevich.banksystem.service.person;

import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;

import java.util.List;

public interface PersonService {

    PersonTO fetchPerson(Long personId) throws ServiceException;

    List<PersonTO> fetchPersons(Long bankId) throws ServiceException;
}
