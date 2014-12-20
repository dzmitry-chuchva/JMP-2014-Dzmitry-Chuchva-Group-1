package com.epam.ns.banksystem.domain.bank;

import com.epam.ns.banksystem.domain.person.PersonTO;

import java.util.List;

public class BankVO {
    private BankTO bankTO;
    private List<PersonTO> personTOs;

    public BankTO getBankTO() {
        return bankTO;
    }

    public void setBankTO(BankTO bankTO) {
        this.bankTO = bankTO;
    }

    public List<PersonTO> getPersonTOs() {
        return personTOs;
    }

    public void setPersonTOs(List<PersonTO> personTOs) {
        this.personTOs = personTOs;
    }
}
