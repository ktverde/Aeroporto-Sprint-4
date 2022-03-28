package br.com.compass.resources;

import br.com.compass.auth.Auth;
import br.com.compass.services.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

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
    @Path("/chooseSeat/{planeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String chooseSeatForward(@PathParam("planeId") String planeId,
                               @Context final HttpServletRequest request,
                               @Context final HttpServletResponse response) throws ServletException, IOException {

      orderService.chooseSeatForward(planeId, request, response);
      return "";
    }

    @Auth
    @POST
    @Path("/makeTicket")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeTicket(@FormParam("seat") String seat,
                                  @FormParam("planeId") String planeId,
                                  @CookieParam("user") String userId) throws Exception {
        return orderService.makeTicket(planeId, seat, userId);
    }

    @GET
    @Path("/getTickets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets() throws Exception {
        return orderService.getTickets();
    }
    @GET
    @Path("/getTickets/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTickets(@PathParam("id") int id) throws Exception {
        return orderService.getTicket(id);
    }
}
