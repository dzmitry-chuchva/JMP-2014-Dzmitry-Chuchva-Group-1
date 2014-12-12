package com.epam.nikitasidorevich.banksystem.dao.person;

import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JPAPersonDAOImpl implements PersonDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BanksystemJPA");

    @Override
    public PersonTO selectPerson(Long personId) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            PersonTO personTO = entityManager.find(PersonTO.class, personId);
            return personTO;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<PersonTO> selectPersons(Long bankId) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<PersonTO> personTOs = entityManager.createQuery("SELECT p FROM PersonTO p").getResultList();
            return personTOs;
        } finally {
            entityManager.close();
        }
    }
}
