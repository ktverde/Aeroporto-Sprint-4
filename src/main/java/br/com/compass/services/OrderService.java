package br.com.compass.services;

import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.PlanesDao;
import br.com.compass.dao.TicketDao;
import br.com.compass.dao.UserDao;
import br.com.compass.exception.IdNotFoundedException;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import br.com.compass.models.Ticket;
import br.com.compass.models.User;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class OrderService
{

    PlanesDao planesDao = new PlanesDao();

    FlightCourseDao flightCourseDao = new FlightCourseDao();

    UserDao userDao = new UserDao();

    TicketDao ticketDao = new TicketDao();

    public void makeOrderForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/makeOrder.jsp").forward(request, response);

    }

    public void chooseSeatForward(String planeId,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {

        Plane plane = planesDao.getFlightById(Integer.parseInt(planeId));
        request.setAttribute("seatList", getSeatsList(plane));
        request.setAttribute("plane", plane);
        request.getRequestDispatcher("/WEB-INF/view/chooseSeat.jsp").forward(request, response);

    }

    public List<Map.Entry<Integer,Boolean>> getSeatsList(Plane plane){
        if(plane!=null)
            return new ArrayList<>(plane.getSeats().entrySet());
        else
            return new ArrayList<>();
    }

    public Response makeTicket(String planeId, String seatId, String userId){
        int planeIntId = Integer.parseInt(planeId);
        int seatIntId = Integer.parseInt(seatId);
        System.out.println(planeId);

        Plane chosenPlane = planesDao.getFlightById(planeIntId);
        chosenPlane.getSeats().put(seatIntId,false);
        planesDao.update(chosenPlane);
        User user = userDao.readId(Long.parseLong(userId));

        Ticket ticket = new Ticket(user,chosenPlane.getFlightCourse(),chosenPlane.getDateFormated(),planeIntId,seatIntId);
        System.out.println(ticket);
        ticketDao.save(ticket);
        System.out.println(ticketDao.readTicket(ticket).getId());
        return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/api/mail/send/"+ticketDao.readTicket(ticket).getId())).build();
    }
    public Response searchFlights(String origin,String destiny, String originDate, String returnDate) {

        StringBuilder mainPlanesId = new StringBuilder("");
        StringBuilder otherPlanesId = new StringBuilder("");

        FlightCourse fc = flightCourseDao.verify(new FlightCourse(origin, destiny));
        System.out.println(fc);
        System.out.println(originDate);
        System.out.println(Date.valueOf(originDate));

        if(fc != null){
            addMain(mainPlanesId,originDate, fc);
        }
        addOthers(mainPlanesId.toString(), otherPlanesId, origin, destiny);
        if(mainPlanesId.length()==0 && otherPlanesId.length()==0) return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/FlightsChoice.xhtml")).build();
        else if(mainPlanesId.length()==0 ) return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/FlightsChoice.xhtml?opi="+otherPlanesId)).build();
        else if(otherPlanesId.length()==0) return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/FlightsChoice.xhtml?mpi="+mainPlanesId)).build();
        else return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/FlightsChoice.xhtml?mpi="+mainPlanesId+"&opi="+otherPlanesId)).build();
    }

    private void addMain(StringBuilder mainPlanesId, String originDate, FlightCourse fc) {

        for (Plane p: planesDao.getMainFlights(fc.getId())) {
            System.out.println(originDate);
            System.out.println(p.getDateFormated());
            SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

            if(dateFormat.format(p.getDate()).equals(originDate)){
                if(mainPlanesId.length()!=0) mainPlanesId.append("-");
                mainPlanesId.append(p.getId());
            }
        }
    }

    public void addOthers(String mainPlanesId, StringBuilder otherPlanesId, String origin, String destiny){
        List<Plane> notFilteredList = planesDao.getFlightsByOriginOrDestiny(origin, destiny);
        if(mainPlanesId.length()==0) {
            for (Plane p: notFilteredList) {
                if (otherPlanesId.length()!=0) otherPlanesId.append("-");
                otherPlanesId.append(p.getId());
            }
        }
        else {
            String[] list = mainPlanesId.split("-");

            boolean check = false;
            for (Plane p1 : notFilteredList) {
                check = false;
                for (String p2 : list) {
                    if (Integer.parseInt(p2) == p1.getId()) {
                        check = true;
                        break;
                    }
                }
                if (!check) {
                    if (otherPlanesId.length()!=0) otherPlanesId.append("-");
                    otherPlanesId.append(p1.getId());
                }
            }
        }

    }

    public Response getTickets() {
        List<Ticket> tickets = ticketDao.readAll();
        String ticketsJson = new Gson().toJson(tickets);
        return Response.ok(ticketsJson).build();
    }

    public Response getTicket(int id) {
        Ticket ticket = ticketDao.readId(id);
        if(ticket == null)
            throw new IdNotFoundedException("Id not Found");
        return Response.ok(ticket.toJson()).build();
    }
}
