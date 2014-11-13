package com.epam.nikitasidorevich.banksystem.service.currency;

import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import java.util.List;

public interface CurrencyService {

    CurrencyTO fetchCurrency(Long currencyId) throws ServiceException;

    List<CurrencyTO> fetchCurrencies() throws ServiceException;
}
