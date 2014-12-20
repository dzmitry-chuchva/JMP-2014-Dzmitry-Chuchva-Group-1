package com.epam.ns.banksystem.dao.account;

import com.epam.ns.banksystem.domain.account.AccountTO;

import java.util.List;

public interface AccountDAO {

    List<AccountTO> selectAccounts(Long bankId, Long personId);

    void updateAccount(AccountTO accountTO);
}
