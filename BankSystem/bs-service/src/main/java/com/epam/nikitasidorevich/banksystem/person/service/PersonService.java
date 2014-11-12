package com.epam.nikitasidorevich.banksystem.person.service;

import com.epam.nikitasidorevich.banksystem.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.person.entity.PersonTO;

import java.util.List;

public interface PersonService {

    List<PersonTO> fetchPersons(Long bankId) throws ServiceException;
}
