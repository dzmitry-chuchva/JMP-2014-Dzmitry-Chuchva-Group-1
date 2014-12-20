package com.epam.ns.banksystem.dao.currency;

import com.epam.ns.banksystem.domain.currency.CurrencyTO;

import java.util.List;

public interface CurrencyDAO {

    CurrencyTO selectCurrency(Long currencyId);

    List<CurrencyTO> selectCurrencies();
}
