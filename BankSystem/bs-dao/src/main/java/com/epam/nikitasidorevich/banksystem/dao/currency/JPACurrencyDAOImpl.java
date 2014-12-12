package com.epam.nikitasidorevich.banksystem.dao.currency;

import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JPACurrencyDAOImpl implements CurrencyDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BanksystemJPA");

    @Override
    public CurrencyTO selectCurrency(Long currencyId) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CurrencyTO currencyTO = entityManager.find(CurrencyTO.class, currencyId);
            return currencyTO;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<CurrencyTO> selectCurrencies() throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<CurrencyTO> currencyTOs = entityManager.createQuery("SELECT c FROM CurrencyTO c").getResultList();
            return currencyTOs;
        } finally {
            entityManager.close();
        }
    }
}
