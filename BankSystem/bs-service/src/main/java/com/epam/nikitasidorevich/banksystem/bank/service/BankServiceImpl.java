package com.epam.nikitasidorevich.banksystem.bank.service;

import com.epam.nikitasidorevich.banksystem.bank.dao.BankDAO;
import com.epam.nikitasidorevich.banksystem.bank.dao.BankDAOImpl;
import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;

import java.util.List;

public class BankServiceImpl implements BankSerivce {

    @Override
    public List<BankTO> fetchBanks() {
        BankDAO bankDAO = new BankDAOImpl();
        List<BankTO> bankTOs = null;
        try {
            bankTOs = bankDAO.selectBanks();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return bankTOs;
    }
}
