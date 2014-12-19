package com.epam.ns.banksystem.service;

import com.epam.ns.banksystem.domain.bank.BankTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bankService")
public class BankServiceImpl implements BankService {

    @Override
    public BankTO fetchBank(Long bankId) {
        BankTO bankTO = new BankTO();
        bankTO.setId(1L);
        bankTO.setName("OLOLOLOLOLO");
        return bankTO;
    }

    @Override
    public List<BankTO> fetchBanks() {
        return null;
    }
}
