package com.epam.nikitasidorevich.banksystem.entity.account;

import com.epam.nikitasidorevich.banksystem.entity.currency.CurrencyTO;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;

import java.util.List;

public class AccountVO {
    private PersonTO personTO;
    private AccountTO accountTO;
    private List<CurrencyTO> currencyTOs;
    private String exchangeRate = "1";

    public PersonTO getPersonTO() {
        return personTO;
    }

    public void setPersonTO(PersonTO personTO) {
        this.personTO = personTO;
    }

    public AccountTO getAccountTO() {
        return accountTO;
    }

    public void setAccountTO(AccountTO accountTO) {
        this.accountTO = accountTO;
    }

    public List<CurrencyTO> getCurrencyTOs() {
        return currencyTOs;
    }

    public void setCurrencyTOs(List<CurrencyTO> currencyTOs) {
        this.currencyTOs = currencyTOs;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountVO accountVO = (AccountVO) o;

        if (!accountTO.equals(accountVO.accountTO)) return false;
        if (!currencyTOs.equals(accountVO.currencyTOs)) return false;
        if (!exchangeRate.equals(accountVO.exchangeRate)) return false;
        if (!personTO.equals(accountVO.personTO)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = personTO.hashCode();
        result = 31 * result + accountTO.hashCode();
        result = 31 * result + currencyTOs.hashCode();
        result = 31 * result + exchangeRate.hashCode();
        return result;
    }
}
