package com.epam.nikitasidorevich.banksystem.account.service;

import com.epam.nikitasidorevich.banksystem.account.entity.AccountVO;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

public interface AccountService {

    AccountVO fetchAccount(Long bankId, Long personId) throws ServiceException;
}
