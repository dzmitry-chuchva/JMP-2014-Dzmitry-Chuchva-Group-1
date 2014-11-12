package com.epam.nikitasidorevich.banksystem.action;

import com.epam.nikitasidorevich.banksystem.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.bank.service.BankSerivce;
import com.epam.nikitasidorevich.banksystem.bank.service.BankServiceImpl;
import com.epam.nikitasidorevich.banksystem.exception.DAOException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

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
