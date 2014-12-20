package com.epam.ns.banksystem.dao.person;

import com.epam.ns.banksystem.domain.person.PersonTO;

import java.util.List;

public interface PersonDAO {

    PersonTO selectPerson(Long personId);

    List<PersonTO> selectPersons(Long bankId);
}
