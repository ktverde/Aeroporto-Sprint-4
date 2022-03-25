package br.com.compass.services;

import br.com.compass.dao.TicketDao;
import br.com.compass.models.Ticket;
import br.com.compass.util.MailSendUtils;

import java.io.IOException;

public class MailService {

    TicketDao ticketDao = new TicketDao();

    public void sendMail(int idTicket){
        Ticket ticket = ticketDao.readId(idTicket);
        try {
            MailSendUtils.sendEmail(ticket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}