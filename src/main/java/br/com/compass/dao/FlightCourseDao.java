package br.com.compass.dao;

import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.FlightCourse;

import javax.persistence.EntityManager;

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

    public FlightCourse verify(FlightCourse flightCourse) {
        try {
            String jpql = "SELECT fc FROM FlightCourse as fc where fc.origin=?1 AND fc.destiny=?2";
            return em.createQuery(jpql, FlightCourse.class)
                    .setParameter(1, flightCourse.getOrigin())
                    .setParameter(2, flightCourse.getDestiny())
                    .getSingleResult();
        }
        catch (Exception e){
            return null;
        }
    }

}
