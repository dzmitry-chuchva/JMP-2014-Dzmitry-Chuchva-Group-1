package com.epam.nikitasidorevich.banksystem.account.entity;

import com.epam.nikitasidorevich.banksystem.currency.entity.CurrencyTO;

public class AccountVO {
    private AccountTO accountTO;
    private CurrencyTO currencyTO;

    public AccountTO getAccountTO() {
        return accountTO;
    }

    public void setAccountTO(AccountTO accountTO) {
        this.accountTO = accountTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountVO)) return false;

        AccountVO accountVO = (AccountVO) o;

        if (accountTO != null ? !accountTO.equals(accountVO.accountTO) : accountVO.accountTO != null) return false;
        if (currencyTO != null ? !currencyTO.equals(accountVO.currencyTO) : accountVO.currencyTO != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountTO != null ? accountTO.hashCode() : 0;
        result = 31 * result + (currencyTO != null ? currencyTO.hashCode() : 0);
        return result;
    }
}
