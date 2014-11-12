package com.epam.nikitasidorevich.banksystem.currency.service;

import com.epam.nikitasidorevich.banksystem.currency.dao.CurrencyDAO;
import com.epam.nikitasidorevich.banksystem.currency.dao.CurrencyDAOImpl;
import com.epam.nikitasidorevich.banksystem.currency.entity.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public CurrencyTO fetchCurrency(Long currencyId) throws ServiceException {
        CurrencyDAO currencyDAO = new CurrencyDAOImpl();
        try {
            CurrencyTO currencyTO = currencyDAO.selectCurrency(currencyId);
            return currencyTO;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
