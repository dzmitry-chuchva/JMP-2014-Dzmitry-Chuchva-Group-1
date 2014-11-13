package com.epam.nikitasidorevich.banksystem.controller.action;

import com.epam.nikitasidorevich.banksystem.controller.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import com.epam.nikitasidorevich.banksystem.service.bank.BankSerivce;
import com.epam.nikitasidorevich.banksystem.service.bank.BankServiceImpl;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewBanksAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        BankSerivce bankSerivce = new BankServiceImpl();
        try {
            List<BankTO> banks = bankSerivce.fetchBanks();
            request.setAttribute("banks", banks);
            return "/jsp/viewBanks.jsp";
        } catch (ServiceException e) {
            throw new ActionException("Something wrong!");
        }
    }
}
