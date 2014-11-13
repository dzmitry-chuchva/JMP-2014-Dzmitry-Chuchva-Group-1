package com.epam.nikitasidorevich.banksystem.service.currency;

import com.epam.nikitasidorevich.banksystem.dao.currency.CurrencyDAO;
import com.epam.nikitasidorevich.banksystem.dao.currency.CurrencyDAOImpl;
import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import java.util.List;

public class CurrencyServiceImpl implements CurrencyService {

    @Override
    public CurrencyTO fetchCurrency(Long currencyId) throws ServiceException {
        CurrencyDAO currencyDAO = CurrencyDAOImpl.getInstance();
        try {
            CurrencyTO currencyTO = currencyDAO.selectCurrency(currencyId);
            return currencyTO;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }

    @Override
    public List<CurrencyTO> fetchCurrencies() throws ServiceException {
        CurrencyDAO currencyDAO = CurrencyDAOImpl.getInstance();
        try {
            List<CurrencyTO> currencyTOs = currencyDAO.selectCurrencies();
            return currencyTOs;
        } catch (DAOException e) {
            throw new ServiceException("Service exception", e);
        }
    }
}
