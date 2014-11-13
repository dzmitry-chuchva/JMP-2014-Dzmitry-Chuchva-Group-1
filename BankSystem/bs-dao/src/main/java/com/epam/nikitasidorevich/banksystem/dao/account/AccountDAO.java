package com.epam.nikitasidorevich.banksystem.dao.account;

import com.epam.nikitasidorevich.banksystem.entity.account.AccountTO;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;

import java.util.List;

public interface AccountDAO {

    List<AccountTO> selectAccounts(Long bankId, Long personId) throws DAOException;

    void updateAccount(Long bankId, Long accountId, Long personId, Long currencyId, Double newTotal) throws DAOException;
}
