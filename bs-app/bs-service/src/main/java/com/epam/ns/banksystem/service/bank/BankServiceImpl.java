package com.epam.ns.banksystem.service.bank;

import com.epam.ns.banksystem.dao.bank.BankDAO;
import com.epam.ns.banksystem.domain.account.AccountTO;
import com.epam.ns.banksystem.domain.account.AccountVO;
import com.epam.ns.banksystem.domain.bank.BankTO;
import com.epam.ns.banksystem.domain.bank.BankVO;
import com.epam.ns.banksystem.domain.person.PersonTO;
import com.epam.ns.banksystem.service.account.AccountService;
import com.epam.ns.banksystem.service.person.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "bankService")
public class BankServiceImpl implements BankService {

    @Resource(name = "bankDAO")
    private BankDAO bankDAO;

    @Resource(name = "personService")
    private PersonService personService;

    @Resource(name = "accountService")
    private AccountService accountService;

    @Override
    public BankTO fetchBank(Long bankId) {
        BankTO bankTO = bankDAO.selectBank(bankId);
        return bankTO;
    }

    @Override
    public List<BankTO> fetchBanks() {
        List<BankTO> bankTOs = bankDAO.selectBanks();
        return bankTOs;
    }

    @Override
    public BankVO fetchBankAndClients(Long bankId) {
        BankTO bankTO = bankDAO.selectBank(bankId);
        List<PersonTO> personTOs = personService.fetchPersons(bankId);

        BankVO bankVO = new BankVO();
        bankVO.setBankTO(bankTO);
        bankVO.setPersonTOs(personTOs);

        return bankVO;
    }

    @Override
    public AccountVO fetchBankClientAccounts(Long bankId, Long personId) {
        AccountVO accountVO = accountService.fetchAccounts(bankId, personId);
        return accountVO;
    }

    @Override
    public void updateAccounts(List<AccountTO> accountTOs) {
        accountService.updateAccounts(accountTOs);
    }
}
