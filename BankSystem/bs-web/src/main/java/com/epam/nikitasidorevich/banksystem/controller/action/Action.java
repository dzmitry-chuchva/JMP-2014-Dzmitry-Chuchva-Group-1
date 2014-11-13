package com.epam.nikitasidorevich.banksystem.controller.action;

import com.epam.nikitasidorevich.banksystem.controller.action.exception.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException;
}
