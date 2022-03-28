package br.com.compass.exception;

import br.com.compass.models.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class IdNotFoundedExceptionMapper implements ExceptionMapper<IdNotFoundedException> {
    @Override
    public Response toResponse(IdNotFoundedException e) {
        ErroMessage mensagem = new ErroMessage(e.getMessage(),Response.Status.NOT_FOUND.getStatusCode(), "http://localhost:8080/Aeroporto_war_exploded/");
        return Response.status(Response.Status.NOT_FOUND).entity(mensagem).build();
    }
}


