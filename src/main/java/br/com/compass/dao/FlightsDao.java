package br.com.compass.dao;

import br.com.compass.models.Plane;
import jakarta.inject.Inject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class FlightsDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Plane plane){
        em.persist(plane);
    }
}
