package com.epam.ns.banksystem.dao.bank;

import com.epam.ns.banksystem.domain.bank.BankTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "bankDAO")
public class BankDAOSpringJDBCImpl implements BankDAO {

    @Override
    public BankTO selectBank(Long bankId) {
        return null;
    }

    @Override
    public List<BankTO> selectBanks() {
        return null;
    }
}
