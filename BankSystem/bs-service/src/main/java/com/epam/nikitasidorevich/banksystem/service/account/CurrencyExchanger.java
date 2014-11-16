package com.epam.nikitasidorevich.banksystem.service.account;

public class CurrencyExchanger {

    public Double calculateExchangedTotal(Double totalCash, Double exchangeRate) {
        Double newTotal = totalCash * exchangeRate;
        return newTotal;
    }
}
