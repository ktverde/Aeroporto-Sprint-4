package br.com.compass.resources;

import br.com.compass.auth.Auth;
import br.com.compass.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;

@Path("/order")
public class OrderResource {

    OrderService orderService = new OrderService();

    @Auth
    @GET
    @Path("/makeOrder")
    public String makeOrderForward(@Context final HttpServletRequest request,
                                   @Context final HttpServletResponse response) throws Exception {
        orderService.makeOrderForward(request, response);
        return "";
    }
}
