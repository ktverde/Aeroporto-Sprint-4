package br.com.compass.tests;

import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.PlanesDao;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;

import java.util.HashMap;
import java.util.Map;

public class PlaneInstaceTest {
    public static void main(String[] args) {
       /* Plane plane =new Plane();
        plane.getSeats().stream().forEach(s->{
            System.out.println(s.getId()+","+s.isAvailable());
        });*/

        FlightCourse flightCourse = new FlightCourse();
        flightCourse.setOrigin("Vitória");
        flightCourse.setDestiny("Rio de Janeiro");
        Plane plane = new Plane();
        plane.setFlightCourse(flightCourse);
        Map<Integer,Boolean> map = new HashMap<>();

        FlightCourseDao flightCourseDao = new FlightCourseDao();
        flightCourseDao.save(flightCourse);


        for(int i = 0; i<186; i++) {
            map.put(i, true);
        }
        plane.setSeats(map);
        PlanesDao planesDao = new PlanesDao();
        planesDao.save(plane);
    }
}
