package com.epam.nikitasidorevich.banksystem.controller.action;

import com.epam.nikitasidorevich.banksystem.controller.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.service.account.AccountService;
import com.epam.nikitasidorevich.banksystem.service.account.AccountServiceImpl;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExchangeCurrenciesAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Long bankId = Long.parseLong(request.getParameter("bankId"));
        Long accountId = Long.parseLong(request.getParameter("accountId"));
        Long personId = Long.parseLong(request.getParameter("clientId"));
        Long currencyId = Long.parseLong(request.getParameter("currencyId"));
        Double rate = Double.parseDouble(request.getParameter("rate"));
        Double totalCash = Double.parseDouble(request.getParameter("totalCash"));
        AccountService accountService = new AccountServiceImpl();
        try {
            accountService.exchangeAccountCurrency(bankId, accountId, personId, currencyId, totalCash, rate);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("Something wrong!", e);
        }
        return "/index.jsp";
    }
}
