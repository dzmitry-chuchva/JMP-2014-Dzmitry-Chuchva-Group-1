package com.epam.ns.banksystem.service.currency;

import com.epam.ns.banksystem.domain.currency.CurrencyTO;

import java.util.List;

public interface CurrencyService {

    CurrencyTO fetchCurrency(Long currencyId);

    List<CurrencyTO> fetchCurrencies();
}
