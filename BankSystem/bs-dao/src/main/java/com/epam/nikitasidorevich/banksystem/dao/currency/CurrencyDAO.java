package com.epam.nikitasidorevich.banksystem.dao.currency;

import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;

import java.util.List;

public interface CurrencyDAO {

    CurrencyTO selectCurrency(Long currencyId) throws DAOException;

    List<CurrencyTO> selectCurrencies() throws DAOException;
}
