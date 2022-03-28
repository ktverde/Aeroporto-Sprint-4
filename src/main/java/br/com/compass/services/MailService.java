package br.com.compass.services;

import br.com.compass.dao.TicketDao;
import br.com.compass.exception.MailSendException;
import br.com.compass.models.Ticket;
import br.com.compass.util.MailSendUtils;

import java.io.IOException;

public class MailService {

    TicketDao ticketDao = new TicketDao();

    public void sendMail(String idTicket){
        Ticket ticket = ticketDao.readId(Integer.parseInt(idTicket));
        try {
            MailSendUtils.sendEmail(ticket);
        } catch (IOException e) {
            throw new MailSendException("Erro ao enviar email");
        }
    }

}