package com.epam.ns.banksystem.service.currency;

import com.epam.ns.banksystem.dao.currency.CurrencyDAO;
import com.epam.ns.banksystem.domain.currency.CurrencyTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "currencyService")
public class CurrencyServiceImpl implements CurrencyService {

    @Resource(name = "currencyDAO")
    private CurrencyDAO currencyDAO;

    @Override
    public CurrencyTO fetchCurrency(Long currencyId) {
        CurrencyTO currencyTO = currencyDAO.selectCurrency(currencyId);
        return currencyTO;
    }

    @Override
    public List<CurrencyTO> fetchCurrencies() {
        List<CurrencyTO> currencyTOs = currencyDAO.selectCurrencies();
        return currencyTOs;
    }
}
