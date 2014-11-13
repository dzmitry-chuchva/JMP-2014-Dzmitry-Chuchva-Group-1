package com.epam.nikitasidorevich.banksystem.service.account;

import com.epam.nikitasidorevich.banksystem.dao.account.AccountDAO;
import com.epam.nikitasidorevich.banksystem.dao.account.AccountDAOImpl;
import com.epam.nikitasidorevich.banksystem.entity.account.AccountTO;
import com.epam.nikitasidorevich.banksystem.entity.account.AccountVO;
import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.service.currency.CurrencyService;
import com.epam.nikitasidorevich.banksystem.service.currency.CurrencyServiceImpl;
import com.epam.nikitasidorevich.banksystem.dao.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;
import com.epam.nikitasidorevich.banksystem.service.person.PersonService;
import com.epam.nikitasidorevich.banksystem.service.person.PersonServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {

    @Override
    public List<AccountVO> fetchAccounts(Long bankId, Long personId) throws ServiceException {
        List<AccountVO> accountVOs = new ArrayList<>();

        PersonService personService = new PersonServiceImpl();
        CurrencyService currencyService = new CurrencyServiceImpl();
        AccountDAO accountDAO = AccountDAOImpl.getInstance();
        try {
            List<AccountTO> accountTOs = accountDAO.selectAccounts(bankId, personId);
            for (AccountTO accountTO : accountTOs) {
                AccountVO accountVO = new AccountVO();

                accountVO.setAccountTO(accountTO);

                PersonTO personTO = personService.fetchPerson(personId);
                accountVO.setPersonTO(personTO);

                List<CurrencyTO> currencyTOs = currencyService.fetchCurrencies();
                accountVO.setCurrencyTOs(currencyTOs);

                accountVOs.add(accountVO);
            }
            return accountVOs;
        } catch (DAOException e) {
            throw new ServiceException("Service error", e);
        }

    }

    private Double calculateExchangedTotal(Double totalCash, Double exchangeRate) {
        Double newTotal = totalCash * exchangeRate;
        return newTotal;
    }

    @Override
    public void exchangeAccountCurrency(Long bankId, Long accountId, Long personId, Long currencyId, Double totalCash, Double exchangeRate) throws ServiceException {
        AccountDAO accountDAO = AccountDAOImpl.getInstance();
        Double newTotal = calculateExchangedTotal(totalCash, exchangeRate);
        try {
            accountDAO.updateAccount(bankId, accountId, personId, currencyId, newTotal);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException("Service exception", e);
        }
    }
}
