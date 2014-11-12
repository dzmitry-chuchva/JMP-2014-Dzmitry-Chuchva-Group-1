package com.epam.nikitasidorevich.banksystem.account.service;

import com.epam.nikitasidorevich.banksystem.account.dao.AccountDAO;
import com.epam.nikitasidorevich.banksystem.account.dao.AccountDAOImpl;
import com.epam.nikitasidorevich.banksystem.account.entity.AccountTO;
import com.epam.nikitasidorevich.banksystem.account.entity.AccountVO;
import com.epam.nikitasidorevich.banksystem.currency.entity.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.currency.service.CurrencyService;
import com.epam.nikitasidorevich.banksystem.currency.service.CurrencyServiceImpl;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public List<AccountVO> fetchAccounts(Long bankId, Long personId) throws ServiceException {
        List<AccountVO> accountVOs = new ArrayList<>();

        CurrencyService currencyService = new CurrencyServiceImpl();
        AccountDAO accountDAO = new AccountDAOImpl();
        try {
            List<AccountTO> accountTOs = accountDAO.selectAccounts(bankId, personId);
            for (AccountTO accountTO : accountTOs) {
                AccountVO accountVO = new AccountVO();
                accountVO.setAccountTO(accountTO);
                Long currencyId = accountTO.getCurrencyId();
                CurrencyTO currencyTO = currencyService.fetchCurrency(currencyId);
                accountVO.setCurrencyTO(currencyTO);
                accountVOs.add(accountVO);
            }
            return accountVOs;
        } catch (DAOException e) {
            throw new ServiceException("Service error", e);
        }

    }
}
