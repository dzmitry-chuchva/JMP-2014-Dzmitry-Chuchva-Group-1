package com.epam.nikitasidorevich.banksystem.account.service;

import com.epam.nikitasidorevich.banksystem.account.dao.AccountDAO;
import com.epam.nikitasidorevich.banksystem.account.dao.AccountDAOImpl;
import com.epam.nikitasidorevich.banksystem.account.entity.AccountTO;
import com.epam.nikitasidorevich.banksystem.account.entity.AccountVO;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

public class AccountServiceImpl implements AccountService {

    @Override
    public AccountVO fetchAccount(Long bankId, Long personId) throws ServiceException {
        AccountDAO accountDAO = new AccountDAOImpl();
//        AccountTO accountTO = accountDAO.selectAccounts(bankId)
        return null;
    }
}
