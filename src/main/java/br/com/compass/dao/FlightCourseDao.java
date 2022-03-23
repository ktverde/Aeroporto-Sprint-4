package br.com.compass.dao;

import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.FlightCourse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FlightCourseDao {

    EntityManager em;

    public FlightCourseDao() {
        this.em = EManagerFactory.getEm();
    }

    public void save(FlightCourse flightCourse){
        em.getTransaction().begin();
        em.persist(flightCourse);
        em.getTransaction().commit();
    }
}
