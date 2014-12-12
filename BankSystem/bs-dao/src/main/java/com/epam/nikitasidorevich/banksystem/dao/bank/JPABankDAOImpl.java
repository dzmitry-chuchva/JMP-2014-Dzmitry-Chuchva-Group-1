package com.epam.nikitasidorevich.banksystem.dao.bank;

import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class JPABankDAOImpl implements BankDAO {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BanksystemJPA");

    @Override
    public BankTO selectBank(Long bankId) throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            BankTO bankTO = entityManager.find(BankTO.class, bankId);
            return bankTO;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<BankTO> selectBanks() throws DAOException {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            List<BankTO> bankTOs = entityManager.createQuery("SELECT b FROM BankTO b").getResultList();
            return bankTOs;
        } finally {
            entityManager.close();
        }
    }
}
