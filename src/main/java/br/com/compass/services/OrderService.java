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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        List<Plane> mainPlanes = new ArrayList<>();
        List<Plane> otherPlanes = new ArrayList<>();

        FlightCourse fc = flightCourseDao.verify(new FlightCourse(origin, destiny));
        System.out.println(fc);
        System.out.println(originDate);
        System.out.println(Date.valueOf(originDate));

        if(fc != null){
            for (Plane p: planesDao.getMainFlights(fc.getId())) {
                System.out.println(p.getDate());
                System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(p.getDate()));
                System.out.println(originDate);
                System.out.println("----------");
                if(new SimpleDateFormat("yyyy-MM-dd").format(p.getDate()).equals(originDate)){
                    mainPlanes.add(p);
                }
            }
            System.out.println(mainPlanes);
        }

        otherPlanes = addOthers(mainPlanes, origin, destiny);
        System.out.println(otherPlanes);


        return Response.status(Response.Status.OK).entity(new ShowPlanes(mainPlanes, otherPlanes)).build();
    }
    public List<Plane> addOthers(List<Plane> mainPlanes, String origin, String destiny){
        List<Plane> notFilteredList = planesDao.getFlightsByOriginOrDestiny(origin, destiny);

        boolean check = false;
        List<Plane> otherPlanes = new ArrayList<>();
        for (Plane p1: notFilteredList) {
            check = false;
            System.out.println(p1.getId());
            System.out.println("---------");
            for (Plane p2: mainPlanes) {
                System.out.println("---------");
                System.out.println(p2.getId());
                System.out.println("---------");
                if(p2.getId() == p1.getId()) {
                    check = true;
                    break;
                }
            }
            if(!check) otherPlanes.add(p1);
        }

        return otherPlanes;
    }
}
