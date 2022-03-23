package br.com.compass.dao;

import br.com.compass.models.FlightCourse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FlightCourseDao {
    @PersistenceContext
    EntityManager em;

    public void save(FlightCourse flightCourse){
        em.persist(flightCourse);
    }
}
