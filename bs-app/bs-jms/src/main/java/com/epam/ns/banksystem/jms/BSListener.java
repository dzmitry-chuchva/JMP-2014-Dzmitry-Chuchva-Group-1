package com.epam.ns.banksystem.jms;

import com.epam.ns.banksystem.dao.account.AccountDAO;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class BSListener implements MessageListener {
    @Resource(name = "accountDAO")
    private AccountDAO accountDAO;

    private void processCommand(String commandLine) {
        String[] cmdArgs = commandLine.split(" ");
        //validate user input somehow

        String command = cmdArgs[0];
        switch (command) {
            case "exchange":
                Long bankId = Long.parseLong(cmdArgs[1]);
                Long personId = Long.parseLong(cmdArgs[2]);
                Long currencyId = Long.parseLong(cmdArgs[3]);
                Double newTotal = Double.parseDouble(cmdArgs[4]);

                System.out.println("I'M HERE!!!");

//                accountDAO.updateAccount(bankId, personId, currencyId, newTotal);
                break;
            default:
                System.out.println("WARNING: Unsupported operation");
                break;
        }
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage;
        try {
            if (message instanceof TextMessage) {
                textMessage = (TextMessage) message;
                //log, kinda
                System.out.println("Received : " + textMessage.getJMSMessageID() + " / \"" + textMessage.getText() + "\" / " + textMessage.getJMSDestination());

                processCommand(textMessage.getText());
            } else {
                System.out.println("Message of wrong type: " + message.getClass().getName());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
