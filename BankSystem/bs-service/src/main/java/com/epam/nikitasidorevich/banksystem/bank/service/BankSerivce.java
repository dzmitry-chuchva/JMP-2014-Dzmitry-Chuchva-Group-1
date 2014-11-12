package com.epam.nikitasidorevich.banksystem.bank.service;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

import java.util.List;

public interface BankSerivce {

    BankTO fetchBank(Long bankId) throws ServiceException;

    List<BankTO>  fetchBanks() throws ServiceException;
}
