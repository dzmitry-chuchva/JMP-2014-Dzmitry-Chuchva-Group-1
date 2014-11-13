package com.epam.nikitasidorevich.banksystem.controller.action;

import com.epam.nikitasidorevich.banksystem.entity.account.AccountVO;
import com.epam.nikitasidorevich.banksystem.service.account.AccountService;
import com.epam.nikitasidorevich.banksystem.service.account.AccountServiceImpl;
import com.epam.nikitasidorevich.banksystem.controller.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageAccountsAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Long bankId = Long.parseLong(request.getParameter("bankId"));
        Long clientId = Long.parseLong(request.getParameter("clientId"));
        AccountService accountService = new AccountServiceImpl();
        try {
            List<AccountVO> accountVOs = accountService.fetchAccounts(bankId, clientId);
            request.setAttribute("accounts", accountVOs);
            return "/jsp/manageAccounts.jsp";
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new ActionException("Something wrong!", e);
        }
    }
}
