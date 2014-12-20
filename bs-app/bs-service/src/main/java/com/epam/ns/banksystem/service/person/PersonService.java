package com.epam.ns.banksystem.service.person;

import com.epam.ns.banksystem.domain.person.PersonTO;

import java.util.List;

public interface PersonService {

    PersonTO fetchPerson(Long personId);

    List<PersonTO> fetchPersons(Long bankId);
}
