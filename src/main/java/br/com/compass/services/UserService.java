package br.com.compass.services;

import br.com.compass.util.Key;
import br.com.compass.dao.UserDao;
import br.com.compass.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class UserService {
    UserDao dao = new UserDao();

    public Response login(User user, String password) {
        try{
            if(user != null && user.getPassword().equals(password))
            {
                String jwtToken = createToken(user.getUsername());

                NewCookie cookie1 = new NewCookie("user", user.getUserId().toString(), "/", "localhost", "user", 60*60, false, true);
                NewCookie cookie2 = new NewCookie("token", "Bearer " + jwtToken, "/", "localhost", "token", 60*60, false, true);
                return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/sucess.jsp")).cookie(cookie1,cookie2).entity(jwtToken).build();
            }
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    private String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("localhost:8080")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(Key.KEY.getPrivate(), SignatureAlgorithm.RS512)
                .compact();
    }

    public Response register(User user) {
        User i = dao.readName(user.getUsername());

        if(i != null){
            login(i, i.getPassword());
        }

        dao.save(user);
        return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/sucess.jsp")).entity("Usuario cadastrado com sucesso!").build();
    }

    public Response listAllUsers() {
        List<User> listUsers = dao.readAll();
        return Response.status(Response.Status.OK).entity(listUsers).build();
    }
}
