package com.epam.nikitasidorevich.banksystem.service.account;

import com.epam.nikitasidorevich.banksystem.dao.account.AccountDAO;
import static org.mockito.Mockito.when;
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
    private AccountDAO accountDAO;

    @Mock
    private CurrencyExchanger currencyExchanger;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCurrencyExchanger() {
        when(currencyExchanger.calculateExchangedTotal(1000D, 0.1)).thenReturn(100D);
    }
}
