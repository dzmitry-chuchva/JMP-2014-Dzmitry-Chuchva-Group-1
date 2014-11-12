package com.epam.nikitasidorevich.banksystem.currency.dao;

import com.epam.nikitasidorevich.banksystem.currency.entity.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

public interface CurrencyDAO {

    CurrencyTO selectCurrency(Long currencyId) throws DAOException;
}
