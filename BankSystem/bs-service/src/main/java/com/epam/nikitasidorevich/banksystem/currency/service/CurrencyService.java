package com.epam.nikitasidorevich.banksystem.currency.service;

import com.epam.nikitasidorevich.banksystem.currency.entity.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

public interface CurrencyService {

    CurrencyTO fetchCurrency(Long currencyId) throws ServiceException;
}
