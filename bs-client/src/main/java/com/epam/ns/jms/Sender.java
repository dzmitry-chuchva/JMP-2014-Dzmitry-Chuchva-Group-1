package com.epam.ns.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.Scanner;

public final class Sender {
    public static final String DESTINATION = "banksystem.queue";
    private static final String EXIT_COMMAND = "q";

    public void sendMessage(String text) {
        ConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        try (
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ) {
            Destination destination = session.createQueue(DESTINATION);
            MessageProducer producer = session.createProducer(destination);
            TextMessage message = session.createTextMessage();
            message.setText(text);
            producer.send(message);

            System.out.println("Sent : " + message.getJMSMessageID() + " / \"" + message.getText() + "\" / " + message.getJMSDestination());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sender sender = new Sender();

        System.out.println("Enter your messages [q=quit]: ");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String text = scanner.nextLine();

            if (!EXIT_COMMAND.equals(text)) {
                sender.sendMessage(text);
            } else {
                System.exit(0);
            }
        }
    }

}
