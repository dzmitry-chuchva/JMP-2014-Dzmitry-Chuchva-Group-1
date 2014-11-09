package com.epam.nikitasidorevich.banksystem.bank.dao;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;

import java.util.List;

public interface BankDAO {

    List<BankTO> selectBanks();
}
