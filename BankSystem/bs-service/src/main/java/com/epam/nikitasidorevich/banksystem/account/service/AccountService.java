package com.epam.nikitasidorevich.banksystem.account.service;

import com.epam.nikitasidorevich.banksystem.account.entity.AccountVO;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

import java.util.List;

public interface AccountService {

    List<AccountVO> fetchAccounts(Long bankId, Long personId) throws ServiceException;
}
