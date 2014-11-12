package com.epam.nikitasidorevich.banksystem.action;

import com.epam.nikitasidorevich.banksystem.action.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageAccountsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Long bankId = Long.parseLong(request.getParameter("bankId"));
        Long clientId = Long.parseLong(request.getParameter("clientId"));
        //TODO - service call here
        return "/jsp/manageAccounts.jsp";
    }
}
