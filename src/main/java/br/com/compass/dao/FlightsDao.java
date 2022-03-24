package br.com.compass.dao;

import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.Plane;
import jakarta.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class FlightsDao {

    private EntityManager em;

    public FlightsDao() {
        this.em = EManagerFactory.getEm();
    }

    public void save(Plane plane){
        em.getTransaction().begin();
        em.persist(plane);
        em.getTransaction().commit();
    }

    public List<Plane> getAllFlights() {
        String jpql = "SELECT Distinct(p) FROM Plane as p JOIN FETCH p.seats";
        return em.createQuery(jpql,Plane.class).getResultList();
    }

    public void delete(Plane plane){
        em.getTransaction().begin();
        plane=em.merge(plane);
        em.remove(plane);
        em.getTransaction().commit();
    }

    public void update(Plane plane) {
        em.getTransaction().begin();
        plane = em.merge(plane);
        em.persist(plane);
        em.getTransaction().commit();
    }
}
