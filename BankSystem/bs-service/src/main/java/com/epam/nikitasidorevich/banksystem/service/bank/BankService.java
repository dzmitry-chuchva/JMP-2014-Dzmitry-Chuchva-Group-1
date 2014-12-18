package com.epam.nikitasidorevich.banksystem.service.bank;

import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import java.util.List;

public interface BankService {

    BankTO fetchBank(Long bankId) throws ServiceException;

    List<BankTO>  fetchBanks() throws ServiceException;
}
