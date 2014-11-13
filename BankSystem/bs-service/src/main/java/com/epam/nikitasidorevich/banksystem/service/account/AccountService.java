package com.epam.nikitasidorevich.banksystem.service.account;

import com.epam.nikitasidorevich.banksystem.entity.account.AccountVO;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import java.util.List;

public interface AccountService {

    List<AccountVO> fetchAccounts(Long bankId, Long personId) throws ServiceException;

    void exchangeAccountCurrency(Long bankId, Long accountId, Long personId, Long currencyId, Double totalCash, Double exchangeRate) throws ServiceException;
}
