package br.com.compass.beans;


import br.com.compass.dao.FlightCourseDao;
import br.com.compass.dao.FlightsDao;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;

import javax.transaction.Transactional;

@Model
public class FlightFormBean {

    FlightCourse flightCourse;
    Plane plane;

    @Inject
    private FlightsDao flightsDao;
    @Inject
    private FlightCourseDao flightCourseDao;

    @Transactional
    public void save(){
        flightCourseDao.save(flightCourse);
        plane.setFlightCourse(flightCourse);
        flightsDao.save(plane);
    }

}
