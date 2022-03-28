package br.com.compass.exception;

import br.com.compass.models.ErroMessage;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        ErroMessage mensagem = new ErroMessage(exception.getMessage(),500,"http://localhost:8080/Aeroporto_war_exploded/");
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mensagem).build();
    }
}