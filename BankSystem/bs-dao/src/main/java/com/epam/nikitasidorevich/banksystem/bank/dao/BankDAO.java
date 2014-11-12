package com.epam.nikitasidorevich.banksystem.bank.dao;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.util.List;

public interface BankDAO {

    BankTO selectBank(Long bankId) throws DAOException;

    List<BankTO> selectBanks() throws DAOException;
}
