package com.epam.nikitasidorevich.banksystem.bank.service;

import com.epam.nikitasidorevich.banksystem.bank.dao.BankDAO;
import com.epam.nikitasidorevich.banksystem.bank.dao.BankDAOImpl;
import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

import java.util.List;

public class BankServiceImpl implements BankSerivce {

    @Override
    public BankTO fetchBank(Long bankId) throws ServiceException {
        BankDAO bankDAO = new BankDAOImpl();
        try {
            BankTO bankTO = bankDAO.selectBank(bankId);
            return bankTO;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }

    @Override
    public List<BankTO> fetchBanks() throws ServiceException {
        BankDAO bankDAO = new BankDAOImpl();
        try {
            List<BankTO> bankTOs = bankDAO.selectBanks();
            return bankTOs;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
