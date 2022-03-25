package br.com.compass.resources;


import br.com.compass.exception.MailSendException;
import br.com.compass.models.User;
import br.com.compass.services.MailService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

@Path("/mail")
public class MailResource {

    private MailService mailService= new MailService();
    @GET
    @Path("/send/{idTicket}")
    public Response sendMail(@PathParam("idTicket") int idTicket){
        mailService.sendMail(idTicket);
        return Response.ok().build();
    }
}