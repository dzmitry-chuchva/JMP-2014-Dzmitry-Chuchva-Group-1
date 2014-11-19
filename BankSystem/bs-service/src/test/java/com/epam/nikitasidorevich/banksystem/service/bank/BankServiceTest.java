package com.epam.nikitasidorevich.banksystem.service.bank;

import com.epam.nikitasidorevich.banksystem.dao.bank.BankDAO;
import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceTest {
    @Mock
    private BankDAO bankDAO;

    @InjectMocks
    private BankSerivce bankSerivce = new BankServiceImpl();

    private BankTO createBank(Long id, String name) {
        BankTO bankTO = new BankTO();
        bankTO.setId(id);
        bankTO.setName(name);
        return bankTO;
    }

    private List<BankTO> createBanks() {
        List<BankTO> bankTOs = new ArrayList<>();
        bankTOs.add(createBank(1L, "TestBank1"));
        bankTOs.add(createBank(2L, "TestBank2"));
        return bankTOs;
    }

    @Test
    public void testFetchBank() throws Exception {
        when(bankDAO.selectBank(anyLong())).thenReturn(createBank(1L, "TestBank1"));
        BankTO bankTO = bankSerivce.fetchBank(555L);
        assertNotNull(bankTO);
    }

    @Test
    public void testFetchBanks() throws Exception {
        when(bankDAO.selectBanks()).thenReturn(createBanks());
        List<BankTO> bankTOs = bankSerivce.fetchBanks();
        for (BankTO bankTO : bankTOs) {
            assertNotNull(bankTO);
        }
    }
}
