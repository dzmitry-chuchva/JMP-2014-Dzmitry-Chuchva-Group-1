package com.epam.nikitasidorevich.banksystem.action;

import com.epam.nikitasidorevich.banksystem.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.bank.service.BankSerivce;
import com.epam.nikitasidorevich.banksystem.bank.service.BankServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewBanksAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        BankSerivce bankSerivce = new BankServiceImpl();
        bankSerivce.fetchBanks();
        return "/jsp/viewBanks.jsp";
    }
}
