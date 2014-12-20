package com.epam.ns.banksystem.dao.bank;

import com.epam.ns.banksystem.domain.bank.BankTO;

import java.util.List;

public interface BankDAO {

    BankTO selectBank(Long bankId);

    List<BankTO> selectBanks();
}
