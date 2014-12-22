package com.epam.ns.banksystem.service.account;

import com.epam.ns.banksystem.dao.account.AccountDAO;
import com.epam.ns.banksystem.domain.account.AccountTO;
import com.epam.ns.banksystem.domain.account.AccountVO;
import com.epam.ns.banksystem.domain.bank.BankTO;
import com.epam.ns.banksystem.domain.currency.CurrencyTO;
import com.epam.ns.banksystem.domain.person.PersonTO;
import com.epam.ns.banksystem.service.bank.BankService;
import com.epam.ns.banksystem.service.currency.CurrencyService;
import com.epam.ns.banksystem.service.person.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "accountService")
public class AccountServiceImpl implements AccountService {

    @Resource(name = "accountDAO")
    private AccountDAO accountDAO;

    @Resource(name = "bankService")
    private BankService bankService;

    @Resource(name = "personService")
    private PersonService personService;

    @Resource(name = "currencyService")
    private CurrencyService currencyService;

    @Override
    public AccountVO fetchAccounts(Long bankId, Long personId) {
        AccountVO accountVO = new AccountVO();

        BankTO bankTO = bankService.fetchBank(bankId);
        accountVO.setBankTO(bankTO);

        PersonTO personTO = personService.fetchPerson(personId);
        accountVO.setPersonTO(personTO);

        List<AccountTO> accountTOs = accountDAO.selectAccounts(bankId, personId);
        accountVO.setAccountTOs(accountTOs);

        List<CurrencyTO> currencyTOs = currencyService.fetchCurrencies();
        accountVO.setCurrencyTOs(currencyTOs);

        return accountVO;
    }

    @Override
    public void updateAccounts(List<AccountTO> accountTOs) {
        accountDAO.batchUpdateAccounts(accountTOs);
    }
}
