package br.com.compass.exception;

import br.com.compass.models.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MailSendExceptionMapper implements ExceptionMapper<MailSendException> {


    @Override
    public Response toResponse(MailSendException exception) {
        ErroMessage mensagem = new ErroMessage(exception.getMessage(),Response.Status.BAD_REQUEST.getStatusCode(),"http://localhost:8080/Aeroporto_war_exploded/");
        return Response.status(Response.Status.BAD_REQUEST).entity(mensagem).build();
    }
}
