package com.epam.nikitasidorevich.banksystem.service.account;

import com.epam.nikitasidorevich.banksystem.dao.account.AccountDAO;

import static org.junit.Assert.assertEquals;

import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AccountServiceTest {
    //since AccountService is an interface
    @InjectMocks
    private AccountService accountService = new AccountServiceImpl();

    @Mock
    private CurrencyExchanger currencyExchanger;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExchanger1() throws ServiceException {
        Double expectedTotal = 100D;
        Double actualTotal = accountService.calculateExchangedTotal(1000D, 0.1);
        assertEquals(expectedTotal, actualTotal);
    }

    @Test(expected = ServiceException.class)
    public void testExchanger2() throws ServiceException {
        Double expectedTotal = 100D;
        Double actualTotal = accountService.calculateExchangedTotal(1000D, -0.1);
    }

    @Test(expected = ServiceException.class)
    public void testExchanger3() throws ServiceException {
        Double expectedTotal = 100D;
        Double actualTotal = accountService.calculateExchangedTotal(-1000D, -0.1);
    }

    @Test(expected = ServiceException.class)
    public void testExchanger4() throws ServiceException {
        Double expectedTotal = 100D;
        Double actualTotal = accountService.calculateExchangedTotal(-1000D, 0.1);
    }

    @Test(timeout = 25)
    public void testExchangeOperationTime1() throws Exception {
        currencyExchanger.calculateExchangedTotal(5000D, 0.5);
    }

    //expected failure here
    @Test(timeout = 1)
    public void testExchangeOperationTime2() throws Exception {
        currencyExchanger.calculateExchangedTotal(5000D, 0.5);
    }
}
