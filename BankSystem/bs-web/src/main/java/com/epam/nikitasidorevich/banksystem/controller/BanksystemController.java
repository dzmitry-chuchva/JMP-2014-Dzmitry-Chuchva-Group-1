package com.epam.nikitasidorevich.banksystem.controller;

import com.epam.nikitasidorevich.banksystem.entity.account.AccountVO;
import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;
import com.epam.nikitasidorevich.banksystem.service.account.AccountService;
import com.epam.nikitasidorevich.banksystem.service.bank.BankService;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.service.person.PersonService;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BanksystemController implements HttpRequestHandler {
    private BankService bankService;
    private PersonService personService;
    private AccountService accountService;

    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String url = null;
        RequestDispatcher requestDispatcher = null;
        try {
            String action = httpServletRequest.getParameter("action");

            switch (action) {

                case "viewBanks":
                    List<BankTO> banks = bankService.fetchBanks();
                    httpServletRequest.setAttribute("banks", banks);
                    url = "/jsp/viewBanks.jsp";
                    requestDispatcher = httpServletRequest.getRequestDispatcher(url);
                    requestDispatcher.forward(httpServletRequest, httpServletResponse);
                    break;

                case "viewClients":
                    Long bankId = Long.parseLong(httpServletRequest.getParameter("bankId"));
                    BankTO bank = bankService.fetchBank(bankId);
                    httpServletRequest.setAttribute("bank", bank);
                    List<PersonTO> persons = personService.fetchPersons(bankId);
                    httpServletRequest.setAttribute("persons", persons);
                    url = "/jsp/viewClients.jsp";
                    requestDispatcher = httpServletRequest.getRequestDispatcher(url);
                    requestDispatcher.forward(httpServletRequest, httpServletResponse);
                    break;

                case "manageAccounts":
                    Long bankId2 = Long.parseLong(httpServletRequest.getParameter("bankId"));
                    Long clientId = Long.parseLong(httpServletRequest.getParameter("clientId"));
                    List<AccountVO> accountVOs = accountService.fetchAccounts(bankId2, clientId);
                    httpServletRequest.setAttribute("accounts", accountVOs);
                    url = "/jsp/manageAccounts.jsp";
                    requestDispatcher = httpServletRequest.getRequestDispatcher(url);
                    requestDispatcher.forward(httpServletRequest, httpServletResponse);
                    break;

                case "exchangeCurrencies":
                    Long bankId3 = Long.parseLong(httpServletRequest.getParameter("bankId"));
                    Long accountId = Long.parseLong(httpServletRequest.getParameter("accountId"));
                    Long personId = Long.parseLong(httpServletRequest.getParameter("clientId"));
                    Long currencyId = Long.parseLong(httpServletRequest.getParameter("currencyId"));
                    Double rate = Double.parseDouble(httpServletRequest.getParameter("rate"));
                    Double totalCash = Double.parseDouble(httpServletRequest.getParameter("totalCash"));
                    accountService.exchangeAccountCurrency(bankId3, accountId, personId, currencyId, totalCash, rate);
                    url = "/index.jsp";
                    requestDispatcher = httpServletRequest.getRequestDispatcher(url);
                    requestDispatcher.forward(httpServletRequest, httpServletResponse);
                    break;

                default:
                    break;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
