package com.epam.nikitasidorevich.banksystem.action;

import com.epam.nikitasidorevich.banksystem.account.service.AccountService;
import com.epam.nikitasidorevich.banksystem.account.service.AccountServiceImpl;
import com.epam.nikitasidorevich.banksystem.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageAccountsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Long bankId = Long.parseLong(request.getParameter("bankId"));
        Long clientId = Long.parseLong(request.getParameter("clientId"));
        AccountService accountService = new AccountServiceImpl();
        try {
            accountService.fetchAccounts(bankId, clientId);
            return "/jsp/manageAccounts.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("Something wrong!", e);
        }
    }
}
