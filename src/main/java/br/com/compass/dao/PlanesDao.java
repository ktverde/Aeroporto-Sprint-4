package br.com.compass.dao;

import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.FlightCourse;
import br.com.compass.models.Plane;
import jakarta.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class PlanesDao {

    private EntityManager em;

    public PlanesDao() {
        this.em = EManagerFactory.getEm();
    }

    public void save(Plane plane){
        em.getTransaction().begin();
        em.persist(plane);
        em.getTransaction().commit();
    }
    public Plane getFlightById(int id){
        String jpql = "SELECT p FROM Plane p WHERE p.id = ?1";
        return em.createQuery(jpql,Plane.class)
                .setParameter(1,id)
                .getSingleResult();
    }

    public List<Plane> getAllFlights() {
        String jpql = "SELECT Distinct(p) FROM Plane as p JOIN FETCH p.seats";
        return em.createQuery(jpql,Plane.class).getResultList();
    }

    public List<Plane> getMainFlights(int id) {

        try{
            String jpql = "SELECT p FROM Plane p where p.flightCourse.id=?1";
            return em.createQuery(jpql, Plane.class)
                    .setParameter(1, id)
                    .getResultList();
        }catch(Exception e){
            return null;
        }
    }

    public List<Plane> getFlightsByOriginOrDestiny(String origin, String destiny) {
        try{
            String jpql = "SELECT p FROM Plane p where p.flightCourse.origin=?1 or p.flightCourse.destiny=?2";
            return em.createQuery(jpql, Plane.class)
                    .setParameter(1, origin)
                    .setParameter(2,destiny)
                    .getResultList();
        }catch(Exception e){
            return null;
        }
    }


    public void delete(Plane plane){
        em.getTransaction().begin();
        plane=em.merge(plane);
        em.remove(plane);
        em.getTransaction().commit();
    }

    public void update(Plane plane) {
        em.getTransaction().begin();
        em.merge(plane);
        em.getTransaction().commit();
    }
}
