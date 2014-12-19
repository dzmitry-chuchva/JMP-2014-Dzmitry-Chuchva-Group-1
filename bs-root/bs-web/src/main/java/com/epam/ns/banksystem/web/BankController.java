package com.epam.ns.banksystem.web;

import com.epam.ns.banksystem.service.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

@Controller(value = "bankController")
public class BankController {

    @Resource(name = "bankService")
    private BankService bankService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        System.out.println(bankService.fetchBank(1L));
        return "home";
    }
}
