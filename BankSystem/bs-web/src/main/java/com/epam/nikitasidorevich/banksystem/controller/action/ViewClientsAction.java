package com.epam.nikitasidorevich.banksystem.controller.action;

import com.epam.nikitasidorevich.banksystem.controller.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.entity.bank.BankTO;
import com.epam.nikitasidorevich.banksystem.service.bank.BankSerivce;
import com.epam.nikitasidorevich.banksystem.service.bank.BankServiceImpl;
import com.epam.nikitasidorevich.banksystem.service.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.entity.person.PersonTO;
import com.epam.nikitasidorevich.banksystem.service.person.PersonService;
import com.epam.nikitasidorevich.banksystem.service.person.PersonServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewClientsAction implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Long bankId = Long.parseLong(request.getParameter("bankId"));
        BankSerivce bankSerivce = new BankServiceImpl();
        PersonService personService = new PersonServiceImpl();
        try {
            BankTO bank = bankSerivce.fetchBank(bankId);
            request.setAttribute("bank", bank);

            List<PersonTO> persons = personService.fetchPersons(bankId);
            request.setAttribute("persons", persons);

            return "/jsp/viewClients.jsp";
        } catch (ServiceException e) {
            throw new ActionException("Somethig wrong!");
        }
    }
}
