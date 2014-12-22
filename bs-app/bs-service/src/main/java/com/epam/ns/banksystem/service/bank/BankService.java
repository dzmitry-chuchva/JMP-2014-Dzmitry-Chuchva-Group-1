package com.epam.ns.banksystem.service.bank;

import com.epam.ns.banksystem.domain.account.AccountTO;
import com.epam.ns.banksystem.domain.account.AccountVO;
import com.epam.ns.banksystem.domain.bank.BankTO;
import com.epam.ns.banksystem.domain.bank.BankVO;

import java.util.List;

public interface BankService {

    BankTO fetchBank(Long bankId);

    List<BankTO> fetchBanks();

    BankVO fetchBankAndClients(Long bankId);

    AccountVO fetchBankClientAccounts(Long bankId, Long personId);

    void updateAccounts(List<AccountTO> accountTOs);
}
