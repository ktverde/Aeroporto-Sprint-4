package br.com.compass.services;

import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.PlanesDao;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class OrderService
{
    PlanesDao planesDao = new PlanesDao();
    FlightCourseDao flightCourseDao = new FlightCourseDao();

    public void makeOrderForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/makeOrder.jsp").forward(request, response);

    }

    public Response makeTicket(String planeId) {
        return Response.status(Response.Status.OK).entity(planeId).build();
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

        return Response.seeOther(URI.create("http://localhost:8080/Aeroporto_war_exploded/FlightsChoice.xhtml?mpi="+mainPlanesId+"&opi="+otherPlanesId)).build();
    }

    private void addMain(StringBuilder mainPlanesId, String originDate, FlightCourse fc) {

        for (Plane p: planesDao.getMainFlights(fc.getId())) {
            System.out.println(originDate);
            System.out.println(p.getDate());
            SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            System.out.println(dateFormat.format(p.getDate()));

            if(dateFormat.format(p.getDate()).equals(originDate)){
                if(!mainPlanesId.isEmpty()) mainPlanesId.append("-");
                mainPlanesId.append(p.getId());
            }
        }
    }

    public void addOthers(String mainPlanesId, StringBuilder otherPlanesId, String origin, String destiny){
        List<Plane> notFilteredList = planesDao.getFlightsByOriginOrDestiny(origin, destiny);
        String[] list = mainPlanesId.split("-");

        boolean check = false;
        for (Plane p1: notFilteredList) {
            check = false;
            for (String p2: list) {
                if(Integer.parseInt(p2) == p1.getId()) {
                    check = true;
                    break;
                }
            }
            if(!check) {
                if(!otherPlanesId.isEmpty()) otherPlanesId.append("-");
                otherPlanesId.append(p1.getId());
            }
        }

    }
}
