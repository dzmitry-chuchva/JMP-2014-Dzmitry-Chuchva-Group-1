package com.epam.ns.banksystem.domain.account;

public class AccountTO {
    private Long id;
    private Long bankId;
    private Long personId;
    private Long currencyId;
    private Double totalCash;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Double getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(Double totalCash) {
        this.totalCash = totalCash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountTO)) return false;

        AccountTO accountTO = (AccountTO) o;

        if (!bankId.equals(accountTO.bankId)) return false;
        if (!currencyId.equals(accountTO.currencyId)) return false;
        if (!id.equals(accountTO.id)) return false;
        if (!personId.equals(accountTO.personId)) return false;
        if (!totalCash.equals(accountTO.totalCash)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + bankId.hashCode();
        result = 31 * result + personId.hashCode();
        result = 31 * result + currencyId.hashCode();
        result = 31 * result + totalCash.hashCode();
        return result;
    }
}
