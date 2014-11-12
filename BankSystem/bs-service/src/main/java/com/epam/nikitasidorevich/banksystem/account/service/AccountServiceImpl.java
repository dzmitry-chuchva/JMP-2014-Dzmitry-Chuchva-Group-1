package com.epam.nikitasidorevich.banksystem.account.service;

import com.epam.nikitasidorevich.banksystem.account.dao.AccountDAO;
import com.epam.nikitasidorevich.banksystem.account.dao.AccountDAOImpl;
import com.epam.nikitasidorevich.banksystem.account.entity.AccountTO;
import com.epam.nikitasidorevich.banksystem.account.entity.AccountVO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public List<AccountVO> fetchAccounts(Long bankId, Long personId) throws ServiceException {
        AccountDAO accountDAO = new AccountDAOImpl();
        try {
            List<AccountTO> accountTOs = accountDAO.selectAccounts(bankId, personId);
            return null;
        } catch (DAOException e) {
            throw new ServiceException("Service error", e);
        }

    }
}
