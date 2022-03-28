package br.com.compass.resources;


import br.com.compass.auth.Auth;
import br.com.compass.services.MailService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/mail")
public class MailResource {

    private MailService mailService= new MailService();

    @Auth
    @GET
    @Path("/send/{idTicket}")
    public Response sendMail(@PathParam("idTicket") String idTicket){
        mailService.sendMail(idTicket);
        return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/sucess.xhtml?iT="+idTicket)).build();
    }
}