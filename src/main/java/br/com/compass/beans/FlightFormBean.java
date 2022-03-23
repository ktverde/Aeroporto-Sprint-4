package br.com.compass.beans;


import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.FlightsDao;
import br.com.compass.dao.SeatsDao;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.faces.annotation.FacesConfig;
import jakarta.inject.Inject;

import javax.transaction.Transactional;

@FacesConfig
@Model
public class FlightFormBean {

    private FlightCourse flightCourse = new FlightCourse();
    private Plane plane = new Plane();

    @Inject
    private FlightsDao flightsDao;
    @Inject
    private FlightCourseDao flightCourseDao;
    @Inject
    private SeatsDao seatsDao;

    public void save(){
        plane.setFlightCourse(flightCourse);
        //System.out.println(plane);
        flightCourseDao.save(flightCourse);
        plane.setFlightCourse(flightCourse);

        plane.getSeats().stream().forEach(s->{
           seatsDao.save(s);
        });

        flightsDao.save(plane);
    }

    public FlightCourse getFlightCourse() {
        return flightCourse;
    }

    public void setFlightCourse(FlightCourse flightCourse) {
        this.flightCourse = flightCourse;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }
}
