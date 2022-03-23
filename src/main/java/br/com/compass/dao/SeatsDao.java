package br.com.compass.dao;

import br.com.compass.factory.EManagerFactory;
import br.com.compass.models.Seat;

import javax.persistence.EntityManager;

public class SeatsDao {

    private EntityManager em;

    public SeatsDao() {
        this.em = EManagerFactory.getEm();
    }

    public void save(Seat seats){
        em.getTransaction().begin();
        em.persist(seats);
        em.getTransaction().commit();
    }
    
}
