package com.epam.ns.banksystem.service;

import com.epam.ns.banksystem.domain.bank.BankTO;

import java.util.List;

public interface BankService {

    BankTO fetchBank(Long bankId);

    List<BankTO> fetchBanks();
}
