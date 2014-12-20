package com.epam.ns.banksystem.service;

import com.epam.ns.banksystem.dao.bank.BankDAO;
import com.epam.ns.banksystem.domain.bank.BankTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "bankService")
public class BankServiceImpl implements BankService {

    @Resource(name = "bankDAO")
    private BankDAO bankDAO;

    @Override
    public BankTO fetchBank(Long bankId) {
        BankTO bankTO = new BankTO();
        bankTO.setId(1L);
        bankTO.setName("OLOLOLOLOLO");
        return bankTO;
    }

    @Override
    public List<BankTO> fetchBanks() {
        List<BankTO> bankTOs = bankDAO.selectBanks();
        return bankTOs;
    }
}
