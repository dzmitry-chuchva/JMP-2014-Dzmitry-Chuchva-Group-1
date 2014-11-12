package com.epam.nikitasidorevich.banksystem.account.dao;

import com.epam.nikitasidorevich.banksystem.account.entity.AccountTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.util.List;

public interface AccountDAO {

    List<AccountTO> selectAccounts(Long bankId, Long personId) throws DAOException;
}
