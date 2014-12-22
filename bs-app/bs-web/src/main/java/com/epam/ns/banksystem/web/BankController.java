package com.epam.ns.banksystem.web;

import com.epam.ns.banksystem.domain.account.AccountVO;
import com.epam.ns.banksystem.domain.bank.BankTO;
import com.epam.ns.banksystem.domain.bank.BankVO;
import com.epam.ns.banksystem.service.bank.BankService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller(value = "bankController")
@SessionAttributes("accountVO")
public class BankController {

    @Resource(name = "bankService")
    private BankService bankService;

    @RequestMapping(value = {"/","/banks"}, method = RequestMethod.GET)
    public String viewBanks(ModelMap modelMap) {
        List<BankTO> bankTOs = bankService.fetchBanks();
        modelMap.addAttribute("banks", bankTOs);
        return "banks";
    }

    @RequestMapping(value = "/bank/{bankId}/clients", method = RequestMethod.GET)
    public String viewBankClients(@PathVariable("bankId") long bankId, ModelMap modelMap) {
        BankVO bankVO = bankService.fetchBankAndClients(bankId);
        modelMap.addAttribute("bankVO", bankVO);
        return "clients";
    }

    @RequestMapping(value = "/bank/{bankId}/client/{clientId}/accounts", method = RequestMethod.GET)
    public String viewClientAccounts(@PathVariable("bankId") long bankId, @PathVariable("clientId") long clientId, ModelMap modelMap) {
        AccountVO accountVO = bankService.fetchBankClientAccounts(bankId, clientId);
        modelMap.addAttribute("accountVO", accountVO);
        return "accounts";
    }

    @RequestMapping(value = "/updateAccount")
    public String updateAccount(@ModelAttribute(value = "accountVO") AccountVO accountVO) {
        bankService.updateAccounts(accountVO.getAccountTOs());
        Long bankId = accountVO.getBankTO().getId();
        return "redirect:/bank/" + bankId + "/clients";
    }
}
