package com.epam.nikitasidorevich.banksystem.service.account;

public class CurrencyExchanger {

    private boolean validateArguments(Double totalCash, Double exchangeRate) {
        if (totalCash < 0 || exchangeRate < 0 || exchangeRate > 1) {
            return false;
        }
        return true;
    }

    public Double calculateExchangedTotal(Double totalCash, Double exchangeRate) {
        Double newTotal = totalCash * exchangeRate;
        return newTotal;
    }
}
