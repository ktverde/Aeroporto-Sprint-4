package br.com.compass.resources;

import br.com.compass.auth.Auth;
import br.com.compass.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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

    @Auth
    @POST
    @Path("/searchFlights")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchFlights(@FormParam("origin") String origin,
                                  @FormParam("destiny") String destiny,
                                  @FormParam("originDate") String originDate,
                                  @FormParam("returnDate") String returnDate) throws Exception {
        return orderService.searchFlights(origin, destiny, originDate, returnDate);
    }

    @Auth
    @Path("/makeTicket/{planeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTicket(@PathParam("planeId") String planeId) throws Exception {
        System.out.println("Printando " + planeId);
        return orderService.makeTicket(planeId);
    }
}
