package br.com.compass.services;

import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.PlanesDao;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import br.com.compass.models.ShowPlanes;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderService
{
    PlanesDao planesDao = new PlanesDao();
    FlightCourseDao flightCourseDao = new FlightCourseDao();

    public void makeOrderForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/makeOrder.jsp").forward(request, response);

    }

    public Response makeTicket() {
        return null;
    }

    public Response searchFlights(String origin,String destiny, String originDate, String returnDate) {
        List<Plane> mainPlanes = null;
        List<Plane> otherPlanes = null;

        FlightCourse fc = flightCourseDao.verify(new FlightCourse(origin, destiny));
        System.out.println(fc);
        System.out.println(originDate);
        System.out.println(Date.valueOf(originDate));

        if(fc != null){
            mainPlanes = planesDao.getMainFlights(fc.getId(), Date.valueOf(originDate));
            System.out.println(mainPlanes);
        }

        otherPlanes = planesDao.getFlightsByOriginOrDestiny(origin, destiny);

        ShowPlanes sp = new ShowPlanes();
        for (Plane p : mainPlanes) sp.addMain(p.getFlightCourse());
        for (Plane p : otherPlanes) sp.addOther(p.getFlightCourse());

        return Response.status(Response.Status.OK).entity(sp).build();
    }
}
