package com.epam.nikitasidorevich.banksystem.dao.account;

import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.entity.account.AccountTO;

import javax.persistence.*;
import java.util.List;

public class JPAAccountDAOImpl implements AccountDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BanksystemJPA");

    @Override
    public List<AccountTO> selectAccounts(Long bankId, Long personId) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT a FROM AccountTO a WHERE a.bankId = :bankId AND a.personId = :personId");
            query.setParameter("bankId", bankId);
            query.setParameter("personId", personId);
            List<AccountTO> accountTOs = query.getResultList();
            return accountTOs;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateAccount(Long bankId, Long accountId, Long personId, Long currencyId, Double newTotal) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Query query = entityManager.createQuery("UPDATE AccountTO a SET a.currencyId = :currencyId, a.totalCash = :totalCash WHERE a.bankId = :bankId AND a.personId = :personId");
            query.setParameter("currencyId", currencyId);
            query.setParameter("totalCash", newTotal);
            query.setParameter("bankId", bankId);
            query.setParameter("personId", personId);
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
}
