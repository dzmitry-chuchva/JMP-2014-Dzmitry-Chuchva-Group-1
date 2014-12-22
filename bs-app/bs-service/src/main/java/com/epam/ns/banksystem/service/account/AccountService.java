package com.epam.ns.banksystem.service.account;

import com.epam.ns.banksystem.domain.account.AccountTO;
import com.epam.ns.banksystem.domain.account.AccountVO;

import java.util.List;

public interface AccountService {

    AccountVO fetchAccounts(Long bankId, Long personId);

    void updateAccounts(List<AccountTO> accountTOs);
}
