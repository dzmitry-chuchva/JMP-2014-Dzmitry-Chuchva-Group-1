package com.epam.nikitasidorevich.banksystem.action;

import com.epam.nikitasidorevich.banksystem.action.exception.ActionException;
import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;
import com.epam.nikitasidorevich.banksystem.bank.service.BankSerivce;
import com.epam.nikitasidorevich.banksystem.bank.service.BankServiceImpl;
import com.epam.nikitasidorevich.banksystem.exception.ServiceException;
import com.epam.nikitasidorevich.banksystem.person.entity.PersonTO;
import com.epam.nikitasidorevich.banksystem.person.service.PersonService;
import com.epam.nikitasidorevich.banksystem.person.service.PersonServiceImpl;

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
