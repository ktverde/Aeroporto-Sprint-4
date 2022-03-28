package br.com.compass.resources;


import br.com.compass.models.Plane;
import br.com.compass.services.FlightsService;
import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/flights")
public class FlightsResource {

    private FlightsService flightsService = new FlightsService();

    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFlightsJson(){
        List<Plane> planesList=flightsService.getAllFlightsJson();
        String planesJson= new Gson().toJson(planesList);
        return Response.ok().entity(planesJson).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSelectedFlightsJson(@PathParam("id") int id){
        Plane plane=flightsService.getSelectedFlightJson(id);
        return Response.ok().entity(plane.toJson()).build();
    }

}
