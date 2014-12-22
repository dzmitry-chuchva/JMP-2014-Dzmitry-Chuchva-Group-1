package com.epam.ns.banksystem.domain.account;

import com.epam.ns.banksystem.domain.bank.BankTO;
import com.epam.ns.banksystem.domain.currency.CurrencyTO;
import com.epam.ns.banksystem.domain.person.PersonTO;

import java.util.List;

public class AccountVO {
    private BankTO bankTO;
    private PersonTO personTO;
    private List<AccountTO> accountTOs;
    private List<CurrencyTO> currencyTOs;

    public BankTO getBankTO() {
        return bankTO;
    }

    public void setBankTO(BankTO bankTO) {
        this.bankTO = bankTO;
    }

    public PersonTO getPersonTO() {
        return personTO;
    }

    public void setPersonTO(PersonTO personTO) {
        this.personTO = personTO;
    }

    public List<AccountTO> getAccountTOs() {
        return accountTOs;
    }

    public void setAccountTOs(List<AccountTO> accountTOs) {
        this.accountTOs = accountTOs;
    }

    public List<CurrencyTO> getCurrencyTOs() {
        return currencyTOs;
    }

    public void setCurrencyTOs(List<CurrencyTO> currencyTOs) {
        this.currencyTOs = currencyTOs;
    }
}
