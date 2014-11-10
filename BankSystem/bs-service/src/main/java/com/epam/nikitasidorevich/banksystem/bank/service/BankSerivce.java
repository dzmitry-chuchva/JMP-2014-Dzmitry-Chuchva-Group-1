package com.epam.nikitasidorevich.banksystem.bank.service;

import com.epam.nikitasidorevich.banksystem.bank.entity.BankTO;

import java.util.List;

public interface BankSerivce {

    List<BankTO>  fetchBanks();
}
