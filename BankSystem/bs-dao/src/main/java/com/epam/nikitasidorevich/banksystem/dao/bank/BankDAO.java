package com.epam.nikitasidorevich.banksystem.dao.bank;

import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;

import java.util.List;

public interface BankDAO {

    BankTO selectBank(Long bankId) throws DAOException;

    List<BankTO> selectBanks() throws DAOException;
}
