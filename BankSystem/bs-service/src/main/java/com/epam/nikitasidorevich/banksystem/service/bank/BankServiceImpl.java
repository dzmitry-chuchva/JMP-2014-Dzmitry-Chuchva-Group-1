package com.epam.nikitasidorevich.banksystem.service.bank;

import com.epam.nikitasidorevich.banksystem.dao.bank.BankDAO;
import com.epam.nikitasidorevich.banksystem.dao.bank.BankDAOImpl;
import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import java.util.List;

public class BankServiceImpl implements BankSerivce {

    private BankDAO bankDAO = BankDAOImpl.getInstance();

    @Override
    public BankTO fetchBank(Long bankId) throws ServiceException {
        try {
            BankTO bankTO = bankDAO.selectBank(bankId);
            return bankTO;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }

    @Override
    public List<BankTO> fetchBanks() throws ServiceException {
        try {
            List<BankTO> bankTOs = bankDAO.selectBanks();
            return bankTOs;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
