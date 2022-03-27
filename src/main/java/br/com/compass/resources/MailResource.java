package br.com.compass.resources;


import br.com.compass.auth.Auth;
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
import java.net.URI;
import java.util.Properties;

@Path("/mail")
public class MailResource {

    private MailService mailService= new MailService();
    @Auth
    @GET
    @Path("/send/{idTicket}")
    public Response sendMail(@PathParam("idTicket") String idTicket){
        mailService.sendMail(idTicket);
        return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/sucess.jsp")).build();
    }
}