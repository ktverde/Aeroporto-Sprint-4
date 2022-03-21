package br.com.compass.resources;


import br.com.compass.auth.Auth;
import br.com.compass.models.User;
import br.com.compass.services.UserService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

@Path("/user")
public class UserResource {
    UserService userService = new UserService();

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password,
                          @QueryParam("name") String name) throws IOException {
        return userService.login(username, password, name);
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user,
                             @FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("name") String name){
        if(user != null) return userService.register(user);
        return userService.register(user = new User(username, password, name));
    }

    @Auth
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers(){
        return userService.listAllUsers();
    }


}
