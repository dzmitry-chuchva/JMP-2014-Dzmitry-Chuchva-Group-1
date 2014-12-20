package com.epam.ns.banksystem.service.person;

import com.epam.ns.banksystem.dao.person.PersonDAO;
import com.epam.ns.banksystem.domain.person.PersonTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "personService")
public class PersonServiceImpl implements PersonService {

    @Resource(name = "personDAO")
    private PersonDAO personDAO;

    @Override
    public PersonTO fetchPerson(Long personId) {
        PersonTO personTO = personDAO.selectPerson(personId);
        return personTO;
    }

    @Override
    public List<PersonTO> fetchPersons(Long bankId) {
        List<PersonTO> personTOs = personDAO.selectPersons(bankId);
        return personTOs;
    }
}
