package com.epam.nikitasidorevich.banksystem.controller;

import com.epam.nikitasidorevich.banksystem.action.Action;
import com.epam.nikitasidorevich.banksystem.action.ActionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BanksystemController extends HttpServlet {
    private ActionManager actionManager = ActionManager.getInstance();

    public BanksystemController() {
        super();
    }

    public void init() throws ServletException {
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String url = null;
        try {
            Action action = actionManager.getAction(request);
            url = action.execute(request, response);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void destroy() {
        super.destroy();
    }
}
